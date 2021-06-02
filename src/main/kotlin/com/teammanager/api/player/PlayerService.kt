package com.teammanager.api.player

import com.teammanager.api.utils.merge
import org.springframework.stereotype.Service
import java.util.*

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
