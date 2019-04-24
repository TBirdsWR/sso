package com.sso.sso.test;

import org.springframework.util.ResourceUtils;

import java.io.*;

public class Test {

    @org.junit.Test
    public void demo() throws IOException {
        File file = ResourceUtils.getFile("classpath:application.yml");
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            String content = "";
            while ((bytesRead = input.read(buf)) > 0) {

                content=new String(buf,0,bytesRead);
            }
            System.out.println(content);
        } finally {
            input.close();
        }
}
}
