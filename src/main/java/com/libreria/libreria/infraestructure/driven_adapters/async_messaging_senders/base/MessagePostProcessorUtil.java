package com.libreria.libreria.infraestructure.driven_adapters.async_messaging_senders.base;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;

//@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagePostProcessorUtil {

    public static MessagePostProcessor getPostProcessMessage() {
        return m -> {
            m.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
            m.getMessageProperties().setContentEncoding("UTF-8");
            return m;
        };
    }
}
