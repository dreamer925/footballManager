package com.dreamer925.footballManager.services.impl;

import com.dreamer925.footballManager.exceptions.EntityNotFoundException;
import com.dreamer925.footballManager.forms.PlayerForm;
import com.dreamer925.footballManager.models.PlayerModel;
import com.dreamer925.footballManager.repositories.PlayerRepository;
import com.dreamer925.footballManager.repositories.TeamRepository;
import com.dreamer925.footballManager.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Override
    public List<PlayerModel> getAll(){
        return playerRepository.findAll();
    }

    @Override
    public PlayerModel getById(long id){
        return playerRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean save(PlayerForm player){
        if (playerRepository.existsBySurnameAndName(player.surname(), player.name())){
            return false;
        }
        playerRepository.save(PlayerModel.of(player, teamRepository.findByName(player.teamName())));
        return true;
    }

    @Override
    public void edit(PlayerForm player, long id){
        PlayerModel playerModel = PlayerModel.of(player, teamRepository.findByName(player.teamName()));
        playerModel.setId(id);
        playerRepository.save(playerModel);
    }

    @Override
    public void delete(long id){
        playerRepository.deleteById(id);
    }
}
