package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithComment() {
        String path = "./dataio/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres")
        );
    }
}