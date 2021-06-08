package com.threads.Tutorial01.Volatile;

public class ThreadsTutorial04 {
    volatile static int i; // значение переменной не кешируются и всем потокам она будет видна 

    public static void main(String[] args) {
        new MyThreadRead().start();
        new MyThreadWrite().start();
    }

    static class MyThreadWrite extends Thread {
        @Override
        public  void run() {
            while (i < 5) {
                System.out.println("increment i to : " + (++i));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThreadRead extends Thread {
        @Override
        public  void run() {
            int localVar = i;
            while (localVar < 5) {
                if(localVar != i) {
                    System.out.println("new value of i " + i);
                    localVar = i;
                }
            }
        }
    }
}
