/**
java.util.concurrent.Semaphore  클래스
사용할 수 있는 리소스의 수가 최대 N개인데 N개보다 많은 수의 쓰레드가 그 리소스를 필요로 하는 경우 Semaphore를 이용하여 
리소스 사용을 제어한다.
 
생서자에서 리소스의 수(permits)를 지정
acquire() - 리소스를 확보. 리소스에 빈자리가 생겼을 경우 바로 쓰레드가 acquire 메소드로부터 곧바로 돌아오게 되고 
세마포어 내부에서는 리소스의 수가 하나 감소. 리소스에 빈자리가 없을 경우 쓰레드는 acquire 메소드 내부에서 블록.
release() - 리소스를 해제. 세마포어 내부에서는 리소스가 하나 증가. acquire 메소드 안에서 대기중인 쓰레드가 있으면
                      그 중 한 개가 깨어나 acquire 메소드로부터 돌아올 수 있다.
**/
package org.kumc.concurent.semaphore;
public class Main {
    public static void main(String[] args) {
        // 3개의 리소스 준비
        BoundedResource resource = new BoundedResource(3);
        
        // 10개의 쓰레드가 사용
        for (int i = 0; i < 10; i++) {
            new UserThread(resource).start();
        }
    }
}
