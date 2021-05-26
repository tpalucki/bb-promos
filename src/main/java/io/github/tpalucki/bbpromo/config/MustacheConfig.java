package io.github.tpalucki.bbpromo.config;

import com.samskivert.mustache.Mustache;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MustacheConfig {

    private static final String DEFAULT_PLACEHOLDER_VALUE = "Some default value";

    @Bean
    public Mustache.Compiler mustacheCompiler(
            Mustache.TemplateLoader templateLoader,
            Environment environment) {

        MustacheEnvironmentCollector collector
                = new MustacheEnvironmentCollector();
        collector.setEnvironment(environment);

        return Mustache.compiler()
                .defaultValue(DEFAULT_PLACEHOLDER_VALUE)
                .withLoader(templateLoader)
                .withCollector(collector);
    }
}
