package org.example.interview.service.impl;

import org.example.interview.entity.Cache;
import org.example.interview.repository.CacheRepository;
import org.example.interview.response.CashResponse;
import org.example.interview.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CacheRepository cacheRepository;

    @Override
    public CashResponse get(String key) {
        Optional<Cache> entry = cacheRepository.findByKey(key);;
        Cache cache = entry.orElse(null);
        return convertToResponse(cache);
    }

    @Override
    public void put(String key, String value) {
            Optional<Cache> entry = cacheRepository.findByKey(key);
            Cache cache = entry.orElse(null);
            if (cache == null) {
                cache = new Cache();
                cache.setKey(key);
            }
            cache.setValue(value);
            cache.setKey(key);
            cacheRepository.save(cache);
    }

    @Override
    public void delete(String key) {
        List<Cache> caches = cacheRepository.findAllByOrderByLastAccessedAsc();
        cacheRepository.deleteByKey(caches.get(0).getKey());
    }

    @Override
    public void clear() {
        cacheRepository.deleteAll();
    }

    @Override
    public Map<String, String> snapshot() {
        return Map.of();
    }

    private CashResponse convertToResponse(Cache cache) {
        if (cache == null) return null;
        return new CashResponse(cache.getId(), cache.getKey(), cache.getValue(), cache.getLastAccessed());
    }


}
