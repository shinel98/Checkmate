package com.likelion.checkmate.aws;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {
    private String endPoint;
    private String regionName;
    private String accessKey;
    private String secretKey;

}