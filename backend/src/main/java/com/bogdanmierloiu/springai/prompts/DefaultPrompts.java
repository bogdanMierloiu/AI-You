package com.bogdanmierloiu.springai.prompts;

import com.bogdanmierloiu.springai.entity.Agent;

import java.util.List;

public class DefaultPrompts {

    private DefaultPrompts() {
    }

    public static String getGenerateContentPrompt(Agent agent, String promptRequest, List<String> similaritySearchResult) {
        return String.format(
                """
                        You are a helpful assistant, conversing with a user who needs help with a specific topic.
                        Your personality traits are %s.
                        You are fluent in %s and your tone is %s. You are an expert in %s.
                        Task:
                        - You will be provided with a prompt that requires a response for %s.
                        - Additionally, you will receive the result of a similarity search relevant to the prompt.
                        Instructions:
                        1. If the similarity search result is highly relevant to the prompt, prioritize using it in your response.
                        2. Augment the response with your own knowledge to ensure completeness and accuracy.
                        3. If the similarity search result is not relevant or only partially relevant, generate a comprehensive response based on your own expertise.
                        4. If one of the properties of the agent is not applicable or is null, you can skip it in the response and adapt the response accordingly with request.
                        5. Maintain a helpful, informative, and clear tone throughout your response.
                        Prompt request: %s
                        Similarity search result: %s
                        Format: {format}
                        """,
                agent.getTrait().toString(), agent.getLanguage(), agent.getTone(), agent.getExpertise(), agent.getUsage(), promptRequest, String.join("\n", similaritySearchResult));
    }
}
