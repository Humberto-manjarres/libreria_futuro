package com.libreria.libreria.application.rabbit;


import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableRabbit
@Data
@Log4j2
@ConfigurationProperties(prefix = "rabbitmq")
public class ReactorRabbitConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int port;
    private String host;
    private String username;
    private String password;
    private String virtualHost;
    private int concurrentConsumers;

    private Integer prefetchCount;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactoryMessage());
        factory.setConcurrentConsumers(concurrentConsumers);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setMessageConverter(producerJackson2MessageConverter());
        factory.setPrefetchCount(prefetchCount);
        return factory;
    }

    @Bean
    public ConnectionFactory connectionFactoryMessage() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
        connectionFactory.setUri("amqp://" + username + ":" + password + "@" + host + ":" + port + "/" + virtualHost);
        logger.debug("CONEXION " + host + ":" + port + " user:" + username + " pass:" + password + " vhost:" + virtualHost);
        connectionFactory.setConnectionLimit(1);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(500);
        backOffPolicy.setMultiplier(10.0);
        backOffPolicy.setMaxInterval(10000);
        retryTemplate.setBackOffPolicy(backOffPolicy);

        rabbitTemplate.setRetryTemplate(retryTemplate);
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
