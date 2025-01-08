package com.dreamer925.footballManager.exceptions;

public class PlayerIsNotInTheTeamException extends RuntimeException {
    public PlayerIsNotInTheTeamException() {
        super("The player is not in the specified team.");
    }
}
