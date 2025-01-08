package com.dreamer925.footballManager.repositories;

import com.dreamer925.footballManager.models.TeamModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamModel, Long> {
    boolean existsByName(String name);

    TeamModel findByName(String s);
}
