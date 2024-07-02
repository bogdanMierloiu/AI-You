package com.bogdanmierloiu.SpringAI.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class OpenAiServiceTest {

    @Autowired
    private OpenAiService openAiService;


    @Test
    void getEmbedding() {
        String text = "Hello, world!";
        float[] embedding = openAiService.getEmbedding(text);
        assert embedding != null;
        System.out.println(Arrays.toString(embedding));
    }

}