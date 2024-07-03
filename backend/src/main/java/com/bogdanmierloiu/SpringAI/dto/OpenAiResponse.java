package com.bogdanmierloiu.SpringAI.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record OpenAiResponse(List<EmbeddingData> data) {

    public record EmbeddingData(float[] embedding) {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EmbeddingData that = (EmbeddingData) o;
            return Arrays.equals(embedding, that.embedding);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(embedding);
        }

        @Override
        public String toString() {
            return "EmbeddingData{" +
                    "embedding=" + Arrays.toString(embedding) +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenAiResponse that = (OpenAiResponse) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "OpenAiResponse{" +
                "data=" + data +
                '}';
    }
}
