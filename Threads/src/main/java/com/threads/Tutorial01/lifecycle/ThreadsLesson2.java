package com.threads.Tutorial01.lifecycle;

public class ThreadsLesson2 {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        //Thread.sleep(5000);
       // Thread.yield(); // це значить що данний поток віддає свій час виконання іншому потоку
        myThread.join(); // тільки коли поток буде мертвий почнеться виконання іншого потоку
        System.out.println("Thread main");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try { 
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
}