package com.schumaker.sql_ai.service;

import com.schumaker.sql_ai.model.SqlRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Service for generating SQL queries using AI.
 * It uses a chat client to interact with an AI model and generate SQL based on a provided schema and question.
 */
@Service
public class SqlAiService {
    private final ChatClient chatClient;

    @Value("classpath:/schema.sql")
    private Resource ddlResource;

    @Value("classpath:/sql-prompt-template.st")
    private Resource sqlPromptTemplateResource;

    public SqlAiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * Generates an SQL query based on the provided request.
     * The request should contain a question that the AI will use to generate the SQL query.
     *
     * @param request the SQL request containing the question to be answered
     * @return the generated SQL query as a String
     * @throws IOException if there is an error reading the DDL resource or prompt template
     */
    public String generateSql(SqlRequest request) throws IOException {
        String schema = ddlResource.getContentAsString(Charset.defaultCharset());

        return chatClient.prompt()
                .options(ChatOptions.builder()
                        .temperature(0.1)
                        .build())
                .user(userSpec -> userSpec
                        .text(sqlPromptTemplateResource)
                        .param("question", request.question())
                        .param("ddl", schema)
                )
                .call()
                .content();
    }
}
