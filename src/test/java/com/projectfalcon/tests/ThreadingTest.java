package com.projectfalcon.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class ThreadingTest extends BaseTest {

    @Test
    public void threadTest1() throws InterruptedException {
        System.out.println("Running test 1 on thread: " + Thread.currentThread().getName());
        Thread.sleep(2000);
    }

    @Test
    public void threadTest2() throws InterruptedException {
        System.out.println("Running test 2 on thread: " + Thread.currentThread().getName());
        Thread.sleep(2000);
    }

    @Test
    public void threadTest3() throws InterruptedException {
        System.out.println("Running test 3 on thread: " + Thread.currentThread().getName());
        Thread.sleep(2000);
    }
}
