/**
java.util.concurrent.locks 패키지의 ReadWriteLock 인터페이스와 ReentrantReadWriteLock 클래스
인스턴스 생성시 락의 취득 순서를 공평(fair)하게 할 것인지를 선택할 수 있다.
공평한 인스턴스를 만든 경우, 
대기시간이 긴 쓰레드가 최우선적으로 락을 취득할 수 있도록 조정된다.
재입장 가능하다. 
즉, 읽기 역할의 쓰레드가 쓰기 위한 락을 취하거나, 쓰기 역할의 쓰레드가 읽기 위한 락을 취하는 것이 가능하다.
쓰기 위한 락을 읽기 위한 락으로 다운그레이드 할 수 있다. 
반대는 안된다.
 **/

package org.kumc.concurent.lock;
public class Main {
    public static void main(String[] args) {
    	
        Data data = new Data(10);
        
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        
        new WriterThread(data, "ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriterThread(data, "abcdefghijklmnopqrstuvwxyz").start();
    }
}
