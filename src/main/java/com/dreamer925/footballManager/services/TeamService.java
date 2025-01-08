package com.dreamer925.footballManager.services;

import com.dreamer925.footballManager.forms.TeamForm;
import com.dreamer925.footballManager.models.TeamModel;

import java.math.BigDecimal;
import java.util.List;

public interface TeamService {
    List<TeamModel> getAll();

    TeamModel getById(long id);

    boolean save(TeamForm team);

    void edit(TeamForm team, long id);

    void delete(long id);

    void transferPlayer(long playerId, long fromTeamId, long toTeamId);

    BigDecimal countTransferCost(BigDecimal playerTransferRate, BigDecimal teamFee);
}
