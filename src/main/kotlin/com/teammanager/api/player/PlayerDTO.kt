package com.teammanager.api.player

import com.teammanager.api.team.TeamEntity
import java.util.*

data class PlayerDTO(
    val id: UUID?,
    val firstName: String,
    val lastName: String,
    val position: Position?,
    val team: TeamEntity?,
    val age: Int?,
    val height: Int?,
    val weight: Int?,
    val skills: Int?
)