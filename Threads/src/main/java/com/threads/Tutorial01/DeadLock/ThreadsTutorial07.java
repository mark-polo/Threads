package com.threads.Tutorial01.DeadLock;

public class ThreadsTutorial07 {
    public static void main(String[] args)  {

        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();
        resourceA.resourceB = resourceB;
        resourceB.resourceA = resourceA;
        MyThreadOne myThreadOne = new MyThreadOne();
        MyThreadTwo myThreadTwo = new MyThreadTwo();
        myThreadOne.resourceA = resourceA;
        myThreadTwo.resourceB = resourceB;
        myThreadOne.start();
        myThreadTwo.start();

    }
}

class MyThreadOne extends Thread {
    ResourceA resourceA;

    @Override
    public void run() {
        System.out.println(resourceA.getI());
    }
}

class MyThreadTwo extends Thread {
    ResourceB resourceB;

    @Override
    public void run() {
        System.out.println(resourceB.getI());
    }
}

class ResourceA {
    ResourceB resourceB;

    public synchronized int getI() {
        return resourceB.returnI();
    }

    public synchronized int returnI() {
        return 1;
    }
}

class ResourceB {
    ResourceA resourceA;

    public synchronized int getI() {
        return resourceA.returnI();
    }

    public synchronized int returnI() {
        return 2;
    }
}