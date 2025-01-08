package com.dreamer925.footballManager.models;

import com.dreamer925.footballManager.forms.PlayerForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Data
@NoArgsConstructor
public class PlayerModel {
    public static final int RATE_COEFFICIENT = 100_000;
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private int age;
    private int monthsOfExperience;
    @JsonIgnore
    @ManyToOne
    private TeamModel team;

    public PlayerModel(String name, String surname, int age, int monthsOfExperience, TeamModel team) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.monthsOfExperience = monthsOfExperience;
        this.team = team;
    }

    public static PlayerModel of(PlayerForm player, TeamModel team){
        return new PlayerModel(player.name(), player.surname(), player.age(), player.monthsOfExperience(), team);
    }

    public BigDecimal getTransferRate(){
        return new BigDecimal(monthsOfExperience * RATE_COEFFICIENT)
                .divide(new BigDecimal(age),2, RoundingMode.HALF_UP);
    }

    public boolean isInTeam(long id) {
        return this.team.getId() == id;
    }
}
