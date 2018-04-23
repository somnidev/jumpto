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
}