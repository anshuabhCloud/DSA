package com.practice;

import java.util.*;

public class FixedWindowRateLimitter implements IRateLimitter {
    Map<Integer,IBucket> userBucket = new HashMap<>();

    final Long windowLength;
    final Long quota;

    public FixedWindowRateLimitter(Long windowLength, Long quota) {
        this.windowLength = windowLength;
        this.quota = quota;
    }

    @Override
    public boolean rateLimitUser(int userId) {
        userBucket.putIfAbsent(userId, new FixedWindowBucket(windowLength, quota));
        return userBucket.get(userId).rateLimit();
    }

}
