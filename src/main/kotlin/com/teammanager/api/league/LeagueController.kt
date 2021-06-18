package com.teammanager.api.league

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/leagues")
class LeagueController(val leagueService: LeagueService) {

    @GetMapping("/all")
    fun getAllLeagues() = try {
        ResponseEntity.ok(leagueService.all())
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PostMapping("/add")
    fun addLeague(@RequestBody leagueDTO: AddLeagueDTO) = try {
        ResponseEntity.ok(leagueService.add(leagueDTO))
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PutMapping("/update/{id}")
    fun updateTeam(@PathVariable id: UUID, @RequestBody leagueDTO: UpdateLeagueDTO): ResponseEntity<Any> {
        assert(leagueDTO.id == id)
        return try {
            ResponseEntity.ok(leagueService.update(leagueDTO))
        } catch (e: Exception) {
            val msg = "Something went wrong: " + e.message
            ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @DeleteMapping("/delete/{id}")
    fun deleteTeam(@PathVariable id: UUID) = try {
        ResponseEntity.ok(leagueService.delete(id))
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }


    @GetMapping("/{id}")
    fun getTeamById(@PathVariable id: UUID) = try {
        ResponseEntity.ok(leagueService.getById(id))
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }



}