package com.practice;

public class TokenBucket  implements IBucket{

    Long quota =0L;
    final Integer refilRate;
    final Integer refilTime;
    final Long maxQuota;
    Long LastRefilTime;


    public TokenBucket(Integer refilRate, int refilTime, Long maxQuota) {
        this.refilRate = refilRate;
        this.refilTime = refilTime;
        this.maxQuota =maxQuota;
        quota= new Long(refilRate);
        Long currentTime = System.currentTimeMillis()/1000;
        LastRefilTime= currentTime - currentTime%refilTime;
        // new Thread(()-> {
        //     try {
        //         this.refill();
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }).start();;
    }

    public void refill()
    {
        Long currentTime = System.currentTimeMillis()/1000;
        Long currentRefillWindow = currentTime-currentTime%refilTime;

        // while(true)
        // {
        if(currentRefillWindow!=LastRefilTime)
        {
            quota = quota+=(refilRate*(currentTime-LastRefilTime)/refilTime);
            if(quota>maxQuota)
                quota=maxQuota;
        }
        LastRefilTime=currentRefillWindow;
    // }

    }



    @Override
    public boolean rateLimit() {
        // TODO Auto-generated method stub
        refill();
        if(quota>0)
        {
            quota-=1;
            return true;
        }
        else
        return false;
    }

}
