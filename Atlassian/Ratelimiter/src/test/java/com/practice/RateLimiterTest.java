package com.practice;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RateLimiterTest {

    @Test
    public void testRateLimitter() throws InterruptedException
    {
        Long window= 3L;
        IRateLimitter rateLimitter = new FixedWindowRateLimitter(window, 3L);
        int userId =1;
        int userId2  =2;
        Assertions.assertTrue(rateLimitter.rateLimitUser(userId));
        Assertions.assertTrue(rateLimitter.rateLimitUser(userId));
        Assertions.assertTrue(rateLimitter.rateLimitUser(userId));
        Assertions.assertTrue(rateLimitter.rateLimitUser(userId2));
        Assertions.assertTrue(rateLimitter.rateLimitUser(userId2));
        Assertions.assertTrue(rateLimitter.rateLimitUser(userId2));
        Assertions.assertFalse(rateLimitter.rateLimitUser(userId2));
        Long currentTime = System.currentTimeMillis();

        Long current = currentTime/1000;
        Long waitTime =  (current +window- current%window)*1000  - currentTime;
        System.out.println("startTime "+ (current-current%window)+ " currentTime "+ currentTime+ " wait time " + waitTime + "after waite "+ (currentTime+waitTime)/1000);
        // waitTime = (waitTime+1)*window-currentTime;
        TimeUnit.MILLISECONDS.sleep(waitTime);
        Assertions.assertTrue(rateLimitter.rateLimitUser(userId2));
        Assertions.assertTrue(rateLimitter.rateLimitUser(userId));
        // TimeUnit.MILLISECONDS.sleep(3500);
        Assertions.assertTrue(rateLimitter.rateLimitUser(userId));
        Assertions.assertTrue(rateLimitter.rateLimitUser(userId));

    }
    @Test
    public void tokenBucketTest() throws InterruptedException
    {
        TokenBucket tokenBucket = new TokenBucket(1, 1, 10L);
        Assertions.assertTrue(tokenBucket.rateLimit());
        Assertions.assertFalse(tokenBucket.rateLimit());
        Assertions.assertFalse(tokenBucket.rateLimit());
        TimeUnit.SECONDS.sleep(3);
        Assertions.assertTrue(tokenBucket.rateLimit());
        Assertions.assertTrue(tokenBucket.rateLimit());
        Assertions.assertTrue(tokenBucket.rateLimit());
        Assertions.assertFalse(tokenBucket.rateLimit());
    }

}
