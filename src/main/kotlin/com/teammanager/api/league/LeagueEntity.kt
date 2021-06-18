package com.teammanager.api.league


import com.teammanager.api.team.TeamEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "league")
class LeagueEntity(
    @Id
    var leagueId: UUID,
    var name: String,
    @OneToMany(
        mappedBy = "league",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var teams: MutableList<TeamEntity> = mutableListOf()
) {
    fun toDto(): LeagueDTO = LeagueDTO(
        this.leagueId,
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
