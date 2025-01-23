package com.libreria.libreria.infraestructure.driven_adapters.async_messaging_senders.base;


import com.libreria.libreria.domain.model.ex.ApplicationLogger;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Log4j2
public class QueueService {

    @Autowired
    protected ApplicationLogger appLogger;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final String rabbitExchange;
    private final String routingKey;
    private final String errorMessage;

    public QueueService(String rabbitExchange, String routingKey, String errorMessage) {
        this.rabbitExchange = rabbitExchange;
        this.routingKey = routingKey;
        this.errorMessage = errorMessage;
    }

    public <R> Mono<Object> sendMessage(R classMessage) {
        try {
            return Mono.fromCallable(() ->
                            rabbitTemplate.convertSendAndReceive(rabbitExchange, routingKey, classMessage, MessagePostProcessorUtil.getPostProcessMessage()))
                    .flatMap(objectResponseEntity -> Mono.just(new Object()))
                    .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(5)));
        } catch (Exception e) {
            appLogger.logError(errorMessage, e);
            return Mono.empty();
        }
    }


}
