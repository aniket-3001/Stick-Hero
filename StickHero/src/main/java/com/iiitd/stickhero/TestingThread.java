package com.iiitd.stickhero;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestingThread extends Thread implements Game{
    @Override
    public void run() {
        Result result = JUnitCore.runClasses(JUnitTests1.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        pillars.clear();
        GameManager.setNumber(0);
    }
}
