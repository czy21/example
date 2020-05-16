package com.team.infrastructure;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

public class IncludeParser {

    public static final String PREFIX = "<%@";
    public static final String SUFFIX = "%>";
    public static final ParserContext templateParserContext = new ParserContext() {
        @Override
        public boolean isTemplate() {
            return true;
        }

        @Override
        public String getExpressionPrefix() {
            return PREFIX;
        }

        @Override
        public String getExpressionSuffix() {
            return SUFFIX;
        }
    };

    public static String parse(String string, Map<String, Object> variables) {
        VariableTokenHandler handler = new VariableTokenHandler(variables);
        GenericTokenParser parser = new GenericTokenParser("<%@", "%>", handler);
        String sql = parser.parse(string);
        if (sql.contains(PREFIX)) {
            return parse(sql, variables);
        }
        return sql;
    }

    public static Expression getExpression(String expression) {
        ExpressionParser parser = new SpelExpressionParser();
        return parser.parseExpression(expression, templateParserContext);
    }

    private static class VariableTokenHandler implements TokenHandler {
        private final Map<String, Object> variables;
        private MapAccessor mapAccessor = new MapAccessor();

        private VariableTokenHandler(Map<String, Object> variables) {
            this.variables = variables;
        }

        @Override
        public String handleToken(String content) {
            StandardEvaluationContext context = new StandardEvaluationContext(variables);
            context.addPropertyAccessor(mapAccessor);
            return IncludeParser.getExpression(StringUtils.join("<%@", content, "%>")).getValue(context, String.class);
        }
    }


}
