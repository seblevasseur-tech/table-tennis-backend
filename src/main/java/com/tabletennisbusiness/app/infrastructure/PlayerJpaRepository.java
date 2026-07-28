package com.tabletennisbusiness.app.infrastructure;

import com.tabletennisbusiness.app.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerJpaRepository extends JpaRepository<Player, Long> {
}
