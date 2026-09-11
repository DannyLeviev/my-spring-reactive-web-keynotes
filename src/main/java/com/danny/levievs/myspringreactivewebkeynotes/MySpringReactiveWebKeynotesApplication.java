package com.danny.levievs.myspringreactivewebkeynotes;

import com.danny.levievs.myspringreactivewebkeynotes.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class MySpringReactiveWebKeynotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringReactiveWebKeynotesApplication.class, args);
    }

}
