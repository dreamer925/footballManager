package com.dreamer925.footballManager.services.impl;

import com.dreamer925.footballManager.exceptions.EntityNotFoundException;
import com.dreamer925.footballManager.exceptions.NotEnoughFoundsException;
import com.dreamer925.footballManager.exceptions.PlayerIsNotInTheTeamException;
import com.dreamer925.footballManager.forms.TeamForm;
import com.dreamer925.footballManager.models.PlayerModel;
import com.dreamer925.footballManager.models.TeamModel;
import com.dreamer925.footballManager.repositories.PlayerRepository;
import com.dreamer925.footballManager.repositories.TeamRepository;
import com.dreamer925.footballManager.services.PlayerService;
import com.dreamer925.footballManager.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    @Override
    public List<TeamModel> getAll(){
        return teamRepository.findAll();
    }

    @Override
    public TeamModel getById(long id){
        return teamRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean save(TeamForm team){
        if (teamRepository.existsByName(team.name())){
            return false;
        }
        teamRepository.save(TeamModel.of(team));
        return true;
    }

    @Override
    public void edit(TeamForm team, long id){
        TeamModel teamModel = TeamModel.of(team);
        teamModel.setId(id);
        teamRepository.save(teamModel);
    }

    @Override
    public void delete(long id){
        teamRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void transferPlayer(long playerId, long fromTeamId, long toTeamId){
        PlayerModel player = playerRepository.findById(playerId).orElseThrow(EntityNotFoundException::new);
        if(!player.isInTeam(fromTeamId)){
            throw new PlayerIsNotInTheTeamException();
        }
        TeamModel fromTeam = teamRepository.findById(fromTeamId).orElseThrow(EntityNotFoundException::new);
        TeamModel toTeam = teamRepository.findById(toTeamId).orElseThrow(EntityNotFoundException::new);
        BigDecimal transferCost = countTransferCost(player.getTransferRate(), toTeam.getFee());
        if (toTeam.getAccount().compareTo(transferCost) < 0){
            throw new NotEnoughFoundsException();
        }
        toTeam.withdrawMoney(transferCost);
        fromTeam.transferMoney(transferCost);
        player.setTeam(toTeam);
    }

    @Override
    public BigDecimal countTransferCost(BigDecimal playerTransferRate, BigDecimal teamFee){
        return playerTransferRate.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)
                .multiply(teamFee).add(playerTransferRate);
    }
}
