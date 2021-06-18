package com.teammanager.api.league

import com.teammanager.api.team.TeamEntityDTO
import java.util.*

data class LeagueDTO(
    var id: UUID,
    var name: String,
    var teams: MutableList<TeamEntityDTO>
)

data class AddLeagueDTO(
    var name: String,
    var teams: MutableList<UUID>?
)



data class UpdateLeagueDTO(
    val id: UUID,
    val name: String?,
    val teams: MutableList<UUID>?,
)