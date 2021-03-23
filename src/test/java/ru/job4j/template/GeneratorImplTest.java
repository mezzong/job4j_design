package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class GeneratorImplTest {

    @Test
    public void whenCorrectThenSuccess() {
        Generator generator = new GeneratorImpl();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Stas");
        args.put("age", "25");
        String template = "I am a ${name}, ${age} y.o";
        String expected = "I am a Stas, 25 y.o";
        String result = generator.produce(template, args);
        assertThat(expected, is(result));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIncorrectKeyThenFailed() {
        Generator generator = new GeneratorImpl();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Stas");
        args.put("age", "25");
        String template = "I am a ${nome}, ${age} y.o";
        String result = generator.produce(template, args);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenExtraKeysThenFailed() {
        Generator generator = new GeneratorImpl();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Stas");
        args.put("age", "25");
        args.put("sex", "true");
        String template = "I am a ${name}, ${age} y.o";
        String result = generator.produce(template, args);
    }
}