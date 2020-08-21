package io.ivanfshz.api.resume.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "resume")
public class ResumeProperties {

    private String path;

}