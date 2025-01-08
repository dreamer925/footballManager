package com.dreamer925.footballManager.services;

import com.dreamer925.footballManager.forms.PlayerForm;
import com.dreamer925.footballManager.models.PlayerModel;

import java.util.List;

public interface PlayerService {
    List<PlayerModel> getAll();

    PlayerModel getById(long id);

    boolean save(PlayerForm player);

    void edit(PlayerForm player, long id);

    void delete(long id);
}
