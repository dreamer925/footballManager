package com.dreamer925.footballManager.repositories;

import com.dreamer925.footballManager.models.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerModel, Long> {
    boolean existsBySurnameAndName(String surname, String name);
}
