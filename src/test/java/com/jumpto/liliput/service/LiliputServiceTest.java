package com.jumpto.liliput.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LiliputServiceTest {

    @Test
    public void generateLiliputTest() {
       LiliputService service = new LiliputService();
       assertEquals("0", service.convert(0));
       assertEquals("1", service.convert(1));
       assertEquals("a", service.convert(10));
       assertEquals("z", service.convert(35));
       assertEquals("10", service.convert(36));
       assertEquals("zz", service.convert(1295));
       assertEquals("100", service.convert(1296));

//       for (int i = 37; i <5000; i++) {
//           service.convert(i);
//       }

    }

    @Test
    public void addUrlWithLiliputTest() {
        LiliputService service = new LiliputService();
        service.addUrlWithLiliput("http://www.heise.de", "heise");
        String heise = service.getLiliputFromUrl("http://www.heise.de");
        assertEquals("heise", heise);

        String testo = service.getLiliputFromUrl("http://www.testo.com");
        assertEquals("testo", testo);
    }

}