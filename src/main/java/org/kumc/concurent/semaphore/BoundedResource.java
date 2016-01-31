package org.kumc.concurent.semaphore;
import java.util.Random;
import java.util.concurrent.Semaphore;
/**
 * 수 제한이 있는 리소스
 */
class BoundedResource {
    private final Semaphore semaphore;
    private final int permits;
    private final static Random random = new Random(314159);
    
    public BoundedResource(int permits) {
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }
    
    public void use() throws InterruptedException {
        semaphore.acquire();
        
        try {
            doUse();
        } finally {
            semaphore.release();
        }
    }
    
    protected void doUse() throws InterruptedException {
        System.out.println("BEGIN: used = " + (permits - semaphore.availablePermits()));
        Thread.sleep(random.nextInt(500));
        System.out.println("END: used = " + (permits - semaphore.availablePermits()));
    }
}
