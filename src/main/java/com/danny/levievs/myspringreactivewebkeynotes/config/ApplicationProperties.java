package com.danny.levievs.myspringreactivewebkeynotes.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Agreement agreement = new Agreement();



    @Getter
    @Setter
    public static class Agreement{
        private String baseUrl;
    }

}
