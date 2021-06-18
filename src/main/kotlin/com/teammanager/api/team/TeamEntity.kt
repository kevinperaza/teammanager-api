package com.teammanager.api.team


import com.teammanager.api.league.LeagueEntity
import com.teammanager.api.player.PlayerEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "team")
class TeamEntity(
    @Id
    var teamId: UUID,
    var name: String,
    var owner: String,
    @OneToMany(
        mappedBy = "team",
        cascade = arrayOf(CascadeType.ALL),
        orphanRemoval = true
    )
    var players: MutableList<PlayerEntity> = mutableListOf(),

    @JoinColumn(name = "leagueId")
    @ManyToOne(fetch = FetchType.LAZY)
    var league: LeagueEntity? = null,
) {
    fun toDto(): TeamEntityDTO = TeamEntityDTO(
        this.teamId,
        this.name,
        this.owner,
        this.players.map { it.toDto() }.toMutableList(),
        this.league?.toDto()
    )

    companion object {
        fun fromDto(dto: TeamEntityDTO): TeamEntity = TeamEntity(
            dto.id,
            dto.name,
            dto.owner,
            dto.players.map {
                PlayerEntity.fromDto(it)
            }.toMutableList(),
            dto.league?.let { LeagueEntity.fromDto(it) }
        )
    }
}