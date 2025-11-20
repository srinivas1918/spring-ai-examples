package com.example.chaptor_two.game;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SpringAiBoardGameService implements BoardGameService {

   /* private static final String questionPromptTemplate = """
    You are a helpful assistant, answering questions about tabletop games.
    If you don't know anything about the game or don't know the answer,
    say "I don't know".

    The game is {gameTitle}.

    The question is: {question}.
    """;*/

    @Value("classpath:/promptTemplate/systemPromptTemplate.st")
    private Resource questionPromptTemplate;


    private ChatClient chatClient;

    private GameRulesService gameRulesService;

    public SpringAiBoardGameService(ChatClient.Builder chatClientBuilder,
                                    GameRulesService gameRulesService){
        chatClient = chatClientBuilder.build();
        this.gameRulesService = gameRulesService;
    }

    @Override
    public Answer askQuestion(Question question) {
        String rules = gameRulesService.getRulesFor(question.gameTitle());
        var answerText = chatClient.prompt()
                .system(systemSpec -> systemSpec
                        .text(questionPromptTemplate)
                        .param("gameTitle", question.gameTitle())
                        .param("rules", rules))
                .user(question.question())
                .call()
                .content();





        return new Answer(answerText, question.gameTitle());
    }
}
