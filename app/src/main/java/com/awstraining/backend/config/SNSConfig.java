package com.awstraining.backend.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class SNSConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.accessKey:#{null}}")
    private String snsAccessKey;

    @Value("${aws.secretKey:#{null}}")
    private String snsSecretKey;
    
    // TODO: lab1
    //  3. Think how to connect with AWS Service from your local pc. 
    @Bean
    AmazonSNS configureSNSClient() {
        if(snsAccessKey != null && snsSecretKey != null) {
            return AmazonSNSClientBuilder.standard().withCredentials(
                    new AWSStaticCredentialsProvider(
                            new BasicAWSCredentials(snsAccessKey, snsSecretKey)
                    )
            ).withRegion(awsRegion).build();
        }
        return AmazonSNSClientBuilder.standard().build();
    }
}
