package com.urise.webapp;

public class MainConcurrency {

    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();
        new Thread(() -> tryDeadLock(object1, object2)).start();
        new Thread(() -> tryDeadLock(object2, object1)).start();
    }

    private static void tryDeadLock(Object obj1, Object obj2) {
        synchronized (obj1) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (obj2) {
                System.out.println("Test");
            }
        }
    }
}
