package com.teammanager.api.team


import com.teammanager.api.player.PlayerEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "team")
class TeamEntity(
    @Id
    var id: UUID,
    var name: String,
    var owner: String,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "team")
    var players: MutableList<PlayerEntity> = mutableListOf(),
    var league: String,
) {
    fun toDto(): TeamDTO = TeamDTO(
        this.id,
        this.name,
        this.owner,
        this.players.map { it.toDto() }.toMutableList(),
        this.league
    )

    companion object {
        fun fromDto(dto: TeamDTO): TeamEntity = TeamEntity(
            dto.id ?: UUID.randomUUID(),
            dto.name,
            dto.owner,
            dto.players.map {
                PlayerEntity.fromDto(it)
            }.toMutableList(),
            dto.league
        )
    }
}