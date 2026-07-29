package com.tabletennisbusiness.app.infrastructure;

import com.tabletennisbusiness.app.model.Rubber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubberJpaRepository extends JpaRepository<Rubber, Long> {
}
