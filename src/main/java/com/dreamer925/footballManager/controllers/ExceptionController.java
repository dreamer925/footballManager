package com.dreamer925.footballManager.controllers;

import com.dreamer925.footballManager.exceptions.EntityNotFoundException;
import com.dreamer925.footballManager.exceptions.NotEnoughFoundsException;
import com.dreamer925.footballManager.exceptions.PlayerIsNotInTheTeamException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(PlayerIsNotInTheTeamException.class)
    public ResponseEntity<String> handlePlayerIsNotInTheTeamException(PlayerIsNotInTheTeamException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(NotEnoughFoundsException.class)
    public ResponseEntity<String> handleNotEnoughFoundsException(NotEnoughFoundsException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
