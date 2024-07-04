package com.bogdanmierloiu.VectorAPI.service;

import com.bogdanmierloiu.SpringAI.VectorAPI.service.OpenAiEmbeddingsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class OpenAiEmbeddingsServiceTest {

    @Autowired
    private OpenAiEmbeddingsService openAiEmbeddingsService;


    @Test
    void getEmbedding() {
        String text = "Hello, world!";
        float[] embedding = openAiEmbeddingsService.getEmbedding(text);
        assert embedding != null;
        System.out.println(Arrays.toString(embedding));
    }

}