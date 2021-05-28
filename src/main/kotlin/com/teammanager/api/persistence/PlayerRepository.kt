package com.teammanager.api.persistence

import com.teammanager.api.models.Player
import org.springframework.data.repository.CrudRepository

interface PlayerRepository : CrudRepository<Player, Long>