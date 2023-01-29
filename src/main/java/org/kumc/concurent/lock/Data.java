package org.kumc.concurent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 
 */
public class Data {
	
	private final char[] buffer;
	private final ReadWriteLock lock = new ReentrantReadWriteLock(true /* fair */);
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();

	public Data(int size) {
		this.buffer = new char[size];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = '*';
		}
	}

	public char[] read() throws InterruptedException {
		readLock.lock();
		try {
			// 시간이 걸리는 Read 작업 
			return doRead();
		} finally {
			readLock.unlock();
		}
	}

	public void write(char c) throws InterruptedException {
		writeLock.lock();
		try {
			// 시간이 걸리는 Write 작업 
			doWrite(c);
		} finally {
			writeLock.unlock();
		}
	}

	private char[] doRead() {
		char[] newbuf = new char[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			newbuf[i] = buffer[i];
		}
		slowly();
		return newbuf;
	}

	private void doWrite(char c) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			slowly();
		}
	}

	private void slowly() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
		}
	}
}
