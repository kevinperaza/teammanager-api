package com.teammanager.api.league

import com.teammanager.api.team.TeamRepository
import com.teammanager.api.utils.merge
import org.springframework.stereotype.Service
import java.util.*

@Service
class LeagueService(
    val leagueRepository: LeagueRepository,
    val teamRepository: TeamRepository
) {

    fun all() = leagueRepository.findAll().map { it.toDto() }

    fun add(leagueDTO: AddLeagueDTO): LeagueDTO {
        val league = LeagueDTO(
            UUID.randomUUID(),
            leagueDTO.name,
            leagueDTO.teams?.map {
                teamRepository.findById(it).get().toDto()
            }?.toMutableList() ?: mutableListOf()
        )


        return leagueRepository.save(LeagueEntity.fromDto(league)).toDto()
    }

    fun update(leagueDTO: UpdateLeagueDTO): LeagueDTO {
        val currentLeague = leagueRepository.findById(leagueDTO.id!!).get().toDto()

        val league = LeagueDTO(
            leagueDTO.id,
            leagueDTO.name ?: currentLeague.name,
            leagueDTO.teams?.map {
                teamRepository.findById(it).get().toDto()
            }?.toMutableList()
                ?: currentLeague.teams
        )

        val updatedTeamDTO = currentLeague merge league

        val updatedLeagueEntity = LeagueEntity.fromDto(updatedTeamDTO)

        return leagueRepository.save(updatedLeagueEntity).toDto()
    }


    fun delete(id: UUID) = leagueRepository.deleteById(id);

    fun getById(id: UUID) = leagueRepository.findById(id).takeIf { it.isPresent }?.get()?.toDto()


}