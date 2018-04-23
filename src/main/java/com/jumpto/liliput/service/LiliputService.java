package com.jumpto.liliput.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LiliputService {

    private static final String CHARACTERSET = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static int CHARACTERSET_LENGTH = CHARACTERSET.length();

    private AtomicInteger counter = new AtomicInteger(CHARACTERSET_LENGTH);

    public String generateLiliput(final String url) {
        int next = counter.getAndIncrement();

        String liliput = convert(next);

        return liliput;
    }

    public String convert(int number) {
        StringBuilder liliput = new StringBuilder();
        System.out.print("" + number);
        if (number >= 0) {
            liliput.append(CHARACTERSET.charAt(number % CHARACTERSET_LENGTH));
            number /= CHARACTERSET_LENGTH;
            while (number > 0) {
                liliput.append(CHARACTERSET.charAt(number % CHARACTERSET_LENGTH));
                number /= CHARACTERSET_LENGTH;
            }
        }
        liliput.reverse();
        System.out.println(" -> "+ liliput.toString());
        return liliput.toString();
    }

}
