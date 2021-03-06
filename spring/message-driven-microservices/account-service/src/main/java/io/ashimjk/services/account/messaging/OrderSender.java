package io.ashimjk.services.account.messaging;

import io.ashimjk.services.messaging.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderSender {

    @Autowired
    private Source source;

    public boolean send(Order order) {
        return this.source.output().send(MessageBuilder.withPayload(order).build());
    }

}
