package com.teammanager.api.team

import com.teammanager.api.player.PlayerRepository
import com.teammanager.api.utils.merge
import org.springframework.stereotype.Service
import java.util.*

@Service
class TeamService(val teamRepository: TeamRepository, val playerRepository: PlayerRepository) {

    fun all() = teamRepository.findAll().map { it.toDto() };

    fun add(teamDTO: AddTeamPayloadDTO): TeamEntityDTO {

        val team = TeamEntityDTO(
            UUID.randomUUID(),
            teamDTO.name,
            teamDTO.owner,
            teamDTO.players?.map {
                playerRepository.findById(it).get().toDto()
            }?.toMutableList() ?: mutableListOf(),
            teamDTO.league,
        )

        return teamRepository.save(TeamEntity.fromDto(team)).toDto();
    }

    fun update(teamDTO: UpdateTeamPayloadDTO): TeamEntityDTO {
        val currentTeam = teamRepository.findById(teamDTO.id!!).get().toDto()

        val team = TeamEntityDTO(
            teamDTO.id,
            teamDTO.name ?: currentTeam.name,
            teamDTO.owner ?: currentTeam.owner,
            teamDTO.players?.map {
                playerRepository.findById(it).get().toDto()
            }?.toMutableList()
                ?: currentTeam.players,
            teamDTO.league,
        )

        val updatedTeamDTO = currentTeam merge team

        val updatedTeamEntity = TeamEntity.fromDto(updatedTeamDTO)

        return teamRepository.save(updatedTeamEntity).toDto()
    }

    fun delete(id: UUID) = teamRepository.deleteById(id);

    fun getById(id: UUID) = teamRepository.findById(id).takeIf { it.isPresent }?.get()?.toDto()


}