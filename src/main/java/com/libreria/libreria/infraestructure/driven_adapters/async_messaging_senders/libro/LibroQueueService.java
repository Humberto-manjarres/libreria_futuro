package com.libreria.libreria.infraestructure.driven_adapters.async_messaging_senders.libro;

import com.libreria.libreria.domain.model.libro.gateway.LibroQueueGateway;
import com.libreria.libreria.infraestructure.driven_adapters.async_messaging_senders.base.QueueService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Log
@Component
public class LibroQueueService extends QueueService implements LibroQueueGateway {

    private static final String ERROR_MESSAGE_RABBIT_EX = "Error al al conectar con la cola de Libro creado";

    public LibroQueueService(@Value("${rabbitmq.components.exchanges}") String rabbitExchange, @Value("${rabbitmq.components.queues.librocreado.routingKey}") String routingKey) {
        super(rabbitExchange, routingKey, ERROR_MESSAGE_RABBIT_EX);
    }

    @Override
    public Mono<Void> libroCreado(Object object) {
        return super.sendMessage(object).subscribeOn(Schedulers.boundedElastic()).then();
    }
}
