package com.teammanager.api.team

import com.teammanager.api.utils.merge
import org.springframework.stereotype.Service
import java.util.*

@Service
class TeamService(val teamRepository: TeamRepository) {

    fun all() = teamRepository.findAll().map { it.toDto() };

    fun add(teamDTO: TeamDTO) =
        teamRepository.save(TeamEntity.fromDto(teamDTO)).toDto();

    fun update(teamDTO: TeamDTO): TeamDTO {
        //TODO: add players to team
        val currentTeam = teamRepository.findById(teamDTO.id!!).get().toDto()

        val updatedTeam = teamRepository.save(TeamEntity.fromDto(currentTeam merge teamDTO))

        return updatedTeam.toDto()
    }

    fun delete(id: UUID) = teamRepository.deleteById(id);

    fun getById(id: UUID) = teamRepository.findById(id).takeIf { it.isPresent }?.get()?.toDto()

}