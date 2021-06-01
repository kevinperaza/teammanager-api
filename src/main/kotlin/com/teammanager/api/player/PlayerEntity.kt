package com.teammanager.api.player

import com.teammanager.api.team.TeamEntity
import java.util.*
import javax.persistence.*

//https://www.section.io/engineering-education/introduction-spring-data/

enum class Position { GOALKEEPER, DEFENDER, MIDFIELDER, STRIKER }

@Entity
@Table(name = "player")
class PlayerEntity(
    @Id
    var id: UUID,
    @ManyToOne
    @JoinColumn(name = "team_id")
    var team: TeamEntity? = null,
    var firstName: String,
    var lastName: String,
    var position: Position? = null,
    var age: Int? = null,
    var height: Int? = null,
    var weight: Int? = null,
    var skills: Int? = null
) {
    fun toDto(): PlayerDTO = PlayerDTO(
        this.id,
        this.firstName,
        this.lastName,
        this.position,
        this.team,
        this.age,
        this.height,
        this.weight,
        this.skills
    )

    companion object {
        fun fromDto(dto: PlayerDTO): PlayerEntity = PlayerEntity(
            dto.id ?: UUID.randomUUID(),
            dto.team,
            dto.firstName,
            dto.lastName,
            dto.position,
            dto.age,
            dto.height,
            dto.weight,
            dto.skills
        )
    }
}