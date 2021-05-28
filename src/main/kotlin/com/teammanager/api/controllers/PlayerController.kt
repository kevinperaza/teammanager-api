package com.teammanager.api.controllers

import com.teammanager.api.models.Player
import com.teammanager.api.persistence.PlayerRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/players")
class PlayerController(val repository: PlayerRepository) {
    @GetMapping("/all")
    fun getAllPlayers() = repository.findAll();

    @PostMapping("/add")
    fun addPlayer(@RequestBody player: Player) = repository.save(player);

    @PutMapping("/update/{id}")
    fun updatePlayer(@PathVariable id: Long, @RequestBody player: Player) {
        assert(player.id == id)
        repository.save(player)
    }

    @DeleteMapping("/delete/{id}")
    fun deletePlayer(@PathVariable id: Long) = repository.deleteById(id);

    @GetMapping("/{id}")
    fun getPlayerById(@PathVariable id: Long) = repository.findById(id);
}