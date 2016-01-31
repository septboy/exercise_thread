package org.kumc.concurent.threadpool;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class Handler implements Runnable {
   private final Socket socket;
   Handler(Socket socket) { this.socket = socket; }
   public void run() {
     // read and service request on socket
   }
 }
