package com.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    /*
    ① ChatClient.Builder
        Spring AI가 자동 설정한 ChatClient.Builder를 생성자 주입으로 받아.
        네가 Spring에서 사용하는 DI와 같은 개념이야.
    ② builder.build()
        AI 모델과 통신할 ChatClient 객체를 생성해.
    ③ prompt()
        AI 모델에 전달할 요청을 구성해.
    ④ system()과 user()
        - system()은 AI의 역할과 답변 규칙을 지정해.
        - user()는 실제 사용자 질문을 전달해.
    ⑤ call().content()
        AI 모델을 호출하고 생성된 답변을 String으로 반환해
    *
    */

    private final ChatClient chatClient;

    public AiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String ask(String question) {
        return chatClient.prompt()
                .system("너는 Java와 Spring 전문가야. 한국어로 설명하고 최대한 상대방이 이해하기 쉽게 설명해줘!.")
                .user(question)
                .call()
                .content();
    }

}
