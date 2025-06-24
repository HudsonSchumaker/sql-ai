package com.schumaker.sql_ai.model;

/**
 * Represents a request for generating SQL queries.
 * Contains a question that the AI will use to generate the SQL query.
 */
public record SqlRequest(String question) {}
