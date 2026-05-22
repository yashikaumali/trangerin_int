package org.example.interview.controller;

import org.example.interview.Request.CacheCreateRequest;
import org.example.interview.response.CashResponse;
import org.example.interview.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("/{key}")
    public ResponseEntity<CashResponse> get(@PathVariable String key) {
        CashResponse val = cacheService.get(key);
        if (val == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(val);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CacheCreateRequest body) {
        cacheService.put(body.getKey(), body.getValue());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Void> delete(@PathVariable String key) {
        cacheService.delete(key);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clear() {
        cacheService.clear();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, String>> all() {
        Map<String, String> snap = cacheService.snapshot();
        List<Map.Entry<String, String>> entries = new ArrayList<>(snap.entrySet());
        Collections.reverse(entries);
        Map<String, String> result = new LinkedHashMap<>();
        for (Map.Entry<String, String> e : entries) {
            result.put(e.getKey(), e.getValue());
        }
        return ResponseEntity.ok(result);
    }
}
