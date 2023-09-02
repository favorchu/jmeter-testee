package com.example.testee;


import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

import static java.lang.Math.abs;


/**
 * Utility collections mocking the application behavior
 *
 * @author Favor
 */
public class MockUtils {

    private static SecureRandom random = new SecureRandom();

    /**
     * To simulate the delay of the process with Thread.sleep()
     *
     * @param randomFrom
     * @param randomTo
     * @throws InterruptedException
     */
    public static void sleep(long randomFrom, long randomTo) throws InterruptedException {
        Thread.currentThread().sleep(abs(randomFrom) + abs(randomFrom - randomTo));
    }

    /**
     * To simulate the delay of the process with busy loop.
     *
     * @param randomFrom
     * @param randomTo
     * @throws InterruptedException
     */
    public static void busy(long randomFrom, long randomTo) {
        long busyTo = System.currentTimeMillis() + abs(randomFrom) + abs(randomFrom - randomTo);
        while (System.currentTimeMillis() <= busyTo)
            ; //Busy loop
    }

    /**
     * To simulate the heap memory usage
     *
     * @param mBytes
     */
    public static void memoryPeek(int mBytes) {
        RandomStringUtils.randomAlphabetic(mBytes * 1024 * 1024);
    }

}
