package org.example.interview.response;

import lombok.Data;
import org.example.interview.entity.Cache;

import java.time.Instant;

@Data
public class CashResponse {
    private Long id;
    private String key;
    private String value;
    private Instant lastAccessed;

    public CashResponse() {
    }

    public CashResponse(Long id, String key, String value, Instant lastAccessed) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.lastAccessed = lastAccessed;
    }

    public static CashResponse fromEntity(Cache c) {
        if (c == null) return null;
        return new CashResponse(c.getId(), c.getKey(), c.getValue(), c.getLastAccessed());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Instant getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Instant lastAccessed) {
        this.lastAccessed = lastAccessed;
    }
}
