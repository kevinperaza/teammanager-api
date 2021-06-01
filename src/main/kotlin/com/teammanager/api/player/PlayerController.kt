package com.teammanager.api.player

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/players")
class PlayerController(val playerService: PlayerService) {
    @GetMapping("/all")
    fun getAllPlayers() = try {
        ResponseEntity.ok(playerService.allPlayers())
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PostMapping("/add")
    fun addPlayer(@RequestBody player: PlayerDTO) = try {
        ResponseEntity.ok(playerService.add(player))
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @PutMapping("/update/{id}")
    fun updatePlayer(@PathVariable id: UUID, @RequestBody player: PlayerDTO): ResponseEntity<Any> {
        return try {
            assert(player.id === id)
            ResponseEntity.ok(playerService.update(player))
        } catch (e: Exception) {
            val msg = "Something went wrong: " + e.message
            ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    };

    @DeleteMapping("/delete/{id}")
    fun deletePlayer(@PathVariable id: UUID) = try {
        ResponseEntity.ok(playerService.delete(id))
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @GetMapping("/{id}")
    fun getPlayerById(@PathVariable id: UUID) = try {
        ResponseEntity.ok(playerService.getById(id))
    } catch (e: Exception) {
        val msg = "Something went wrong: " + e.message
        ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}