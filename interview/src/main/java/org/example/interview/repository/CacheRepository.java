package org.example.interview.repository;

import org.example.interview.entity.Cache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CacheRepository extends JpaRepository<Cache, Long> {
    Optional<Cache> findByKey(String key);
    void deleteByKey(String key);

    List<Cache> findAllByOrderByLastAccessedDesc();
}

