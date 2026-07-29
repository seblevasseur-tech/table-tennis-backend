package com.tabletennisbusiness.app.infrastructure;

import com.tabletennisbusiness.app.model.Blade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BladeJpaRepository extends JpaRepository<Blade, Long> {
}
