package com.awstraining.backend.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ComprehendSentimentConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.accessKey:#{null}}")
    private String snsAccessKey;

    @Value("${aws.secretKey:#{null}}")
    private String snsSecretKey;

    @Bean
    AmazonComprehend configureComprehendClient() {
        AmazonTranslate configureTranslatorClient() {
            if(snsAccessKey != null && snsSecretKey != null) {
                return AmazonComprehendClientBuilder.standard().withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(snsAccessKey, snsSecretKey)
                        )
                ).withRegion(awsRegion).build();
            }
            return AmazonComprehendClientBuilder.standard().build();
    }
}
