package com.andrey.dotamanager.util;

import java.util.Random;

public class RandomGenerator {
    private static final Random random = new Random();

    public static double getRandomDouble() {
        return random.nextDouble();
    }
}

