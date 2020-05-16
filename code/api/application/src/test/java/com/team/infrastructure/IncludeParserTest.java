package com.team.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class IncludeParserTest {

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void parseTest() throws IOException {
        Object vars = getMock();

        Map<String, Object> c = (Map<String, Object>) ((Map<String, Object>) vars).get("c");

        String ret = IncludeParser.parse(c.get("sql").toString(), (Map<String, Object>) vars);

        System.out.println("aa");

    }


    public Object getMock() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("includeTest.json");
        return mapper.readValue(stream, Map.class);
    }

}