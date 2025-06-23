package com.schumaker.sql_ai.view;

import com.schumaker.sql_ai.model.SqlRequest;
import com.schumaker.sql_ai.model.SqlResponse;
import com.schumaker.sql_ai.service.SqlAiService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/chat")
public class SqlAiController {

    private final SqlAiService sqlAiService;
    private final JdbcTemplate jdbcTemplate;

    public SqlAiController(SqlAiService sqlAiService, JdbcTemplate jdbcTemplate) {
        this.sqlAiService = sqlAiService;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Endpoint to generate SQL query based on the provided request.
     * The request should contain a question that the AI will use to generate the SQL query.
     *
     * @param sqlRequest the SQL request containing the question to be answered
     * @return the generated SQL query as a String
     * @throws IOException if there is an error generating the SQL query
     */
    @PostMapping("/sql")
    public String getSQL(@RequestBody SqlRequest sqlRequest) throws IOException {
        return sqlAiService.generateSql(sqlRequest).trim();
    }

    /**
     * Endpoint to execute the generated SQL query and return the results.
     * If the generated query is a SELECT statement, it will return the results as a list.
     *
     * @param sqlRequest the SQL request containing the question to be answered
     * @return a SqlResponse containing the SQL query and the results if applicable
     * @throws IOException if there is an error generating or executing the SQL query
     */
    @PostMapping
    public SqlResponse getValues(@RequestBody SqlRequest sqlRequest) throws IOException {
        var sqlQuery = sqlAiService.generateSql(sqlRequest).trim();
        if (sqlQuery.toLowerCase().startsWith("select")) {
            return new SqlResponse(sqlQuery, jdbcTemplate.queryForList(sqlQuery));
        }
        return new SqlResponse(sqlQuery, null);
    }
}
