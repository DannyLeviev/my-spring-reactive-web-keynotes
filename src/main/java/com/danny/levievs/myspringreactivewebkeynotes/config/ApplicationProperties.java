package com.danny.levievs.myspringreactivewebkeynotes.config;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Agreement agreement;



    @Getter
    @Setter
    public static class Agreement{
        private String baseUrl;
    }

}
