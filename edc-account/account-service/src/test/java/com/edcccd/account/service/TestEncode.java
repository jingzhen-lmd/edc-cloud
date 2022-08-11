package com.edcccd.account.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
public class TestEncode {

    @Test
    public void encode() {
        try (FileInputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\test\\123.txt");
             BufferedInputStream bf = new BufferedInputStream(inputStream);
             BufferedOutputStream bfo = new BufferedOutputStream(new FileOutputStream("C:\\Users\\Administrator" +
                     "\\Desktop\\test\\456.txt"));
        ) {

            byte[] bytes = new byte[1024 * 1024];
            int read = bf.read(bytes);
            while (read > 0) {
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i]=bytes[i];
                }

                bfo.write(bytes);
                read = bf.read(bytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
