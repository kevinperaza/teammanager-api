package com.teammanager.api.team

import com.teammanager.api.league.LeagueDTO
import com.teammanager.api.league.LeagueEntity
import com.teammanager.api.player.PlayerDTO
import java.util.*

data class TeamEntityDTO(
    val id: UUID,
    val name: String,
    val owner: String,
    var players: MutableList<PlayerDTO>,
    val league: LeagueDTO?,
)

data class AddTeamPayloadDTO (
    val name: String,
    val owner: String,
    val players: MutableList<UUID>?,
    val league: UUID?,
)

data class UpdateTeamPayloadDTO (
    val id: UUID,
    val name: String?,
    val owner: String?,
    var players: MutableList<UUID>?,
    val league: UUID?,
)