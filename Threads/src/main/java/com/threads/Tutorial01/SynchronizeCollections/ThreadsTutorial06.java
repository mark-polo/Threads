package com.threads.Tutorial01.SynchronizeCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadsTutorial06 {

    public static void main(String[] args)  {
        NameList nameList = new NameList();
        nameList.add("Me");
        
        class MyThread extends Thread {
            @Override
            public void run() {
                System.out.println(nameList.removeFirst()) ;
            }
        }

        MyThread myThread = new MyThread();
        myThread.setName("One");
        myThread.start();
        new MyThread().start();
    }

    static class NameList {
//        private final List<String> list = Collections.synchronizedList(new ArrayList<>());
        private final List<String> list = new ArrayList<>();

        public synchronized void add(String name) {
            list.add(name);
        }

        public synchronized String removeFirst() {
            if (list.size() > 0) {
                if(Thread.currentThread().getName().equals("One")) {
                    Thread.yield();
                }
                return list.remove(0);
            }
            return null;
        }
    }
}