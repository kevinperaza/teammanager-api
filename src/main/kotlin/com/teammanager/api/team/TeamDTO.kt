package com.teammanager.api.team

import com.teammanager.api.player.PlayerDTO
import java.util.*

data class TeamEntityDTO(
    val id: UUID,
    val name: String,
    val owner: String,
    var players: MutableList<PlayerDTO>,
    val league: String?,
)

data class AddTeamPayloadDTO (
    val name: String,
    val owner: String,
    val players: MutableList<UUID>?,
    val league: String?,
)

data class UpdateTeamPayloadDTO (
    val id: UUID,
    val name: String?,
    val owner: String?,
    var players: MutableList<UUID>?,
    val league: String?,
)