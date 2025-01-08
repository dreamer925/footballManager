package com.dreamer925.footballManager.models;

import com.dreamer925.footballManager.forms.TeamForm;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TeamModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal fee;
    private BigDecimal account;
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private List<PlayerModel> players;

    public TeamModel(String name, BigDecimal fee, BigDecimal account, List<PlayerModel> players) {
        this.name = name;
        this.fee = fee;
        this.account = account;
        this.players = players;
    }

    public TeamModel(String name, BigDecimal fee, BigDecimal account) {
        this.name = name;
        this.fee = fee;
        this.account = account;
        this.players = Collections.emptyList();
    }

    public static TeamModel of(TeamForm team){
        return new TeamModel(team.name(), team.fee(), team.account(), team.players());
    }

    public void transferMoney(BigDecimal amount){
        this.account = account.add(amount);
    }

    public void withdrawMoney(BigDecimal amount){
        this.account = account.subtract(amount);
    }
}
