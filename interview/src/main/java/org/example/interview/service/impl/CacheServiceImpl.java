package org.example.interview.service.impl;

import org.example.interview.entity.Cache;
import org.example.interview.repository.CacheRepository;
import org.example.interview.response.CashResponse;
import org.example.interview.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CacheRepository cacheRepository;

    @Override
    @Transactional
    public CashResponse get(String key) {
        Optional<Cache> entry = cacheRepository.findByKey(key);
        if (entry.isPresent()) {
            Cache cache = entry.get();
            // Update lastAccessed time to current time
            cache.setLastAccessed(java.time.Instant.now());
            cacheRepository.save(cache);
            return convertToResponse(cache);
        }
        return null;
    }

    @Override
    @Transactional
    public void put(String key, String value) {
        Optional<Cache> entry = cacheRepository.findByKey(key);
        Cache cache = entry.orElse(null);
        if (cache == null) {
            cache = new Cache();
            cache.setKey(key);
        }
        cache.setValue(value);
        cache.setKey(key);
        cache.setLastAccessed(java.time.Instant.now());
        cacheRepository.save(cache);
    }

    @Override
    @Transactional
    public void delete(String key) {
        cacheRepository.deleteByKey(key);
    }

    @Override
    @Transactional
    public void clear() {
        cacheRepository.deleteAll();
    }

    @Override
    public List<CashResponse> getAll() {
        List<Cache> caches  = cacheRepository.findAllByOrderByLastAccessedDesc();
        return convertToResponseList(caches);
    }

    private CashResponse convertToResponse(Cache cache) {
        if (cache == null) return null;
        return new CashResponse(cache.getId(), cache.getKey(), cache.getValue(), cache.getLastAccessed());
    }

    private List<CashResponse> convertToResponseList(List<Cache> caches) {
        if (caches == null) return null;
        return caches.stream()
                .map(cache -> new CashResponse(cache.getId(), cache.getKey(), cache.getValue(), cache.getLastAccessed()))
                .toList();
    }


}
