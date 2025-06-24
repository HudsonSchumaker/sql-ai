package com.schumaker.sql_ai.model;

import java.util.List;
import java.util.Map;

/**
 * Represents a response containing the generated SQL query and its results.
 * This record holds the SQL query as a string and the results as a list of maps,
 * where each map represents a row with column names as keys and their corresponding values.
 */
public record SqlResponse(String sqlQuery, List<Map<String, Object>> results) {}
