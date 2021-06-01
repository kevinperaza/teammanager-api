package com.teammanager.api.player

import org.springframework.stereotype.Service
import java.util.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor


inline infix fun <reified T : Any> T.merge(other: T): T {
    val nameToProperty = T::class.declaredMemberProperties.associateBy { it.name }
    val primaryConstructor = T::class.primaryConstructor!!
    val args = primaryConstructor.parameters.associateWith { parameter ->
        val property = nameToProperty[parameter.name]!!

        (property.get(other) ?: property.get(this))
    }
    return primaryConstructor.callBy(args)
}

@Service
class PlayerService(val playerRepository: PlayerRepository) {
    fun allPlayers() = playerRepository.findAll().map { it.toDto() }

    fun add(player: PlayerDTO) = playerRepository.save(PlayerEntity.fromDto(player)).toDto();

    fun update(player: PlayerDTO): PlayerDTO {
        val currentPlayer = playerRepository.findById(player.id!!).get().toDto()

        val updatedPlayer = playerRepository.save(PlayerEntity.fromDto(currentPlayer merge player))

        return updatedPlayer.toDto()
    }

    fun delete(id: UUID) = playerRepository.deleteById(id);

    fun getById(id: UUID) = playerRepository.findById(id).takeIf { it.isPresent }?.get()?.toDto()
}
