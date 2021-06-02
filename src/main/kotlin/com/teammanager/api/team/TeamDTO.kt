package com.teammanager.api.team

import com.teammanager.api.player.PlayerDTO
import java.util.*

data class TeamDTO (
    val id: UUID?,
    val name: String,
    val owner: String,
    val players: MutableList<PlayerDTO>,
    val league: String,
)