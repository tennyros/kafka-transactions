package com.github.tennyros.transferservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(KafkaPropertiesConfig.class)
public class KafkaConfig {

    private final KafkaPropertiesConfig kafkaProperties;

    @Bean
    public NewTopic createWithdrawTopic() {
        return TopicBuilder.name(kafkaProperties.getTopics().get("withdraw-money"))
                .partitions(kafkaProperties.getPartitions())
                .replicas(kafkaProperties.getReplicas())
                .build();
    }

    @Bean
    public NewTopic createDepositTopic() {
        return TopicBuilder.name(kafkaProperties.getTopics().get("deposit-money"))
                .partitions(kafkaProperties.getPartitions())
                .replicas(kafkaProperties.getReplicas())
                .build();
    }

}
