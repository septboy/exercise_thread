package org.kumc.concurent.semaphore;

import java.util.Random;

/**
 * 리소스를 사용하는 쓰레드
 */
class UserThread extends Thread {
    private final static Random random = new Random(26535);
    private final BoundedResource resource;
    
    public UserThread(BoundedResource resource){
    	this.resource = resource;
    }
    
    public void run() {
        try {
            while (true) {
                resource.use();
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
        
        }
    }
}
