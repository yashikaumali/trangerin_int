package org.example.interview.Request;

import lombok.Data;

@Data
public class CacheCreateRequest {
    private String key;
    private String value;
}
