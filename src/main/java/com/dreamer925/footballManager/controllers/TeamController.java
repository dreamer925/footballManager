package com.dreamer925.footballManager.controllers;

import com.dreamer925.footballManager.forms.TeamForm;
import com.dreamer925.footballManager.models.TeamModel;
import com.dreamer925.footballManager.services.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/team")
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public List<TeamModel> getAllTeams(){
        return teamService.getAll();
    }

    @GetMapping("/{id}")
    public TeamModel getTeamById(@PathVariable("id") long id){
        return teamService.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> addTeam(@Valid @RequestBody TeamForm team, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().toString());
        }
        if (teamService.save(team)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Team added successfully.");
        }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team already exists.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editTeam(@PathVariable("id") long id, @Valid @RequestBody TeamForm team, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors().toString());
        }
        teamService.edit(team,id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Team with id = " + id + " updated successfully.");
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> transferPlayer(@RequestParam long playerId, @RequestParam long fromTeamId,
                                                 @RequestParam long toTeamId){
        teamService.transferPlayer(playerId, fromTeamId, toTeamId);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Player transferred successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable("id") long id){
        teamService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Team with id = " + id + " deleted successfully.");
    }
}
