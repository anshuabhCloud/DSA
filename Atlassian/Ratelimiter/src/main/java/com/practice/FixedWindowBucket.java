package com.practice;

public class FixedWindowBucket implements IBucket {

    Long startTime=0L;
    final Long windowLength;
    final Long quota;

    public FixedWindowBucket(Long windowLength, Long quota) {
        this.windowLength = windowLength;
        this.quota = quota;
        // startTime=System.currentTimeMillis()/1000;
    }
    Long usedQuota=0L;

    /*
     *window size = 5
     *  2 - 1
     * 9
     */

    @Override
    public boolean rateLimit() {
        Long currentTime = System.currentTimeMillis()/1000;
        System.out.println(currentTime+ "start time "+ startTime+ "  count "+ usedQuota);
        // System.out.println(startTime);
        // System.out.println(windowLength);


        if(startTime+windowLength<=currentTime)
        {
            startTime= currentTime-  currentTime%windowLength;
            usedQuota=1L;
            return true;
        } else{
            if(usedQuota<quota)
            {
                usedQuota+=1;
                return true;
            }
            return false;
        }
    }

}
