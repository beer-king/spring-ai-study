package com.service;

import com.dto.AiStudyResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated //메서드 검증을 활성화하는 데 사용할 수 있다 검증할 메서드에 @Valid 사용
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

    @Valid // DTO에 @NotBlank, @Min등의 객체 내부의 필드를 검증한다!!
    @NotNull // 반환된 AiStudyResponse "객체 자체"가 null인지 검증!!
    public AiStudyResponse askStructured(String question){
        return chatClient.prompt()
                .system("""
                        너는 Java 개발 교육 담당자야.
                                            질문에 한국어로 답변해.
                                            difficulty는 난이도를 1~5 사이 정수로 표시해.
                                            example에는 간단한 예시를 작성해.
                        """)
                .user(question)
                .call()
                .entity(AiStudyResponse.class); // DTO 반환

    }

    @NotNull
    @Valid
    public AiStudyResponse testValidation() {
        return new AiStudyResponse(
                "",
                "의존성 주입",
                "생성자 주입",
                10
        );
    }
}
