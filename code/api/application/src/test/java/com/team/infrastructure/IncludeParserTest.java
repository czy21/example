package com.team.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Test
    public void test() {
        List<String> seq = List.of("a", "b","c");

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < seq.size(); i++) {
            if (seq.size() == 1) {
                result.append(seq.get(0));
                break;
            }
            if (i + 1 < seq.size()) {
                result.append(StringUtils.join("ifnull(", seq.get(i), ","));
            }
            if (i + 1 == seq.size()) {
                result.append(StringUtils.join(seq.get(i), String.join("", Collections.nCopies(i, ")"))));
            }

        }

        System.out.println(result);

    }

}