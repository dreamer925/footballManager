package com.dreamer925.footballManager.controllers;

import com.dreamer925.footballManager.forms.PlayerForm;
import com.dreamer925.footballManager.models.PlayerModel;
import com.dreamer925.footballManager.services.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/player")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public List<PlayerModel> getAllPlayers(){
        return playerService.getAll();
    }

    @GetMapping("/{id}")
    public PlayerModel getPlayerById(@PathVariable("id") long id){
        return playerService.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> addPlayer(@Valid @RequestBody PlayerForm player, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().toString());
        }
        if (playerService.save(player)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Player added successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player already exists.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editPlayer(@PathVariable("id") long id, @Valid @RequestBody PlayerForm player, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().toString());
        }
        playerService.edit(player,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Player with id = " + id + " updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable("id") long id){
        playerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Player with id = " + id + " deleted successfully.");
    }
}
