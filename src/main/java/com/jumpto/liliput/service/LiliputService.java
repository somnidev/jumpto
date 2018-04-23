package com.jumpto.liliput.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LiliputService {

    private static final String CHARACTERSET = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static int CHARACTERSET_LENGTH = CHARACTERSET.length();

    // TODO: Get from database...
    private AtomicInteger counter = new AtomicInteger(CHARACTERSET_LENGTH);

    public String generateLiliput(final String url) {
        int next = counter.getAndIncrement();

        String liliput = convert(next);
        return liliput;
    }

    // TODO: move to tools class...
    public String convert(int number) {
        StringBuilder liliput = new StringBuilder();
        System.out.print("" + number);
        if (number >= 0) {
            do {
                liliput.append(CHARACTERSET.charAt(number % CHARACTERSET_LENGTH));
                number /= CHARACTERSET_LENGTH;
            } while (number > 0);
        }
        return liliput.reverse().toString();
    }

}
