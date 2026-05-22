package org.example.interview.service;

import org.example.interview.response.CashResponse;

import java.util.List;
import java.util.Map;

public interface CacheService {

    CashResponse get(String key);

    void put(String key, String value);

    void delete(String key);

    void clear();

    List<CashResponse> getAll();
}
