package com.teammanager.api.team

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/teams")
class TeamController(val teamService: TeamService) {
    @GetMapping("/all")
    fun getAllTeams() = try {
        ResponseEntity.ok(teamService.all())
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PostMapping("/add")
    fun addTeam(@RequestBody teamDTO: AddTeamPayloadDTO) = try {
        ResponseEntity.ok(teamService.add(teamDTO))
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }


    @PutMapping("/update/{id}")
    fun updateTeam(@PathVariable id: UUID, @RequestBody teamDTO: UpdateTeamPayloadDTO): ResponseEntity<Any> {
        assert(teamDTO.id == id)
        return try {
            ResponseEntity.ok(teamService.update(teamDTO))
        } catch (e: Exception) {
            val msg = "Something went wrong: " + e.message
            ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteTeam(@PathVariable id: UUID) = try {
        ResponseEntity.ok(teamService.delete(id))
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }


    @GetMapping("/{id}")
    fun getTeamById(@PathVariable id: UUID) = try {
        ResponseEntity.ok(teamService.getById(id))
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}