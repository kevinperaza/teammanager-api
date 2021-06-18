package com.teammanager.api.league

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LeagueRepository: JpaRepository<LeagueEntity, UUID>