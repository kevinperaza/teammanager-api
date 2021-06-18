package com.teammanager.api.league


import com.teammanager.api.team.TeamEntity
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Id
import javax.persistence.OneToMany

class LeagueEntity(
    @Id
    var id: UUID,
    var name: String,
    @OneToMany(
        mappedBy = "league", cascade = arrayOf(CascadeType.ALL),
        orphanRemoval = true
    )
    var teams: MutableList<TeamEntity> = mutableListOf()
){
    fun toDto(): LeagueDTO = LeagueDTO(
        this.id,
        this.name,
        this.teams.map { it.toDto() }.toMutableList(),
    )

    companion object {
        fun fromDto(dto: LeagueDTO): LeagueEntity = LeagueEntity(
            dto.id,
            dto.name,
            dto.teams.map {
                TeamEntity.fromDto(it)
            }.toMutableList(),
        )
    }
}
