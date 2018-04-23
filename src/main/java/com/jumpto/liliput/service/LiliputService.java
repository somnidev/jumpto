package com.jumpto.liliput.service;

import com.jumpto.liliput.model.Url;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LiliputService {

    private static final String CHARACTERSET = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static int CHARACTERSET_LENGTH = CHARACTERSET.length();


    // TODO: Get initial value from repository...
    private AtomicInteger counter = new AtomicInteger(CHARACTERSET_LENGTH);

    // TODO: Use a repository...
    private HashMap<String, String> liliputs = new HashMap<>();
    private HashMap<String, String> url2liliput = new HashMap<>();

    public LiliputService() {
        addUrlWithLiliput("http://www.testo.com","testo");
    }


    public String generateLiliput(final String url) {
        String liliput;
        do {
            int next = counter.getAndIncrement();
            liliput = convert(next);
        } while (liliputs.containsKey(liliput)); // see constructor... ;)
        return liliput;
    }

    public String getUrlFromLiliput(String liliput) {
        return liliputs.getOrDefault(liliput, "http://liliput.com");
    }

    public String getLiliputFromUrl(String url) {
        String md5Hex = DigestUtils.md5DigestAsHex(url.getBytes());
        return url2liliput.get(md5Hex);
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

    public void addUrlWithLiliput(String url, String liliput) {
        liliputs.put(liliput, url);
        final String md5Hex = DigestUtils.md5DigestAsHex(url.getBytes());
        url2liliput.put(md5Hex, liliput);
    }
}
