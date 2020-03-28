package io.ashimjk.spring.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RabbitmqMessagingApplication {

    public static final String EXCHANGE_NAME = "ajk_exchange";
    public static final String QUEUE = "ajk_queue";
    public static final String ROUTING_KEY = "ajk_routing_key";

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqMessagingApplication.class, args);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Binding queueToExchangeBinding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //@Bean
    //public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
    //    SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
    //    listenerContainer.setConnectionFactory(connectionFactory);
    //    listenerContainer.setQueueNames(QUEUE);
    //    listenerContainer.setMessageListener(System.out::println);
    //    return listenerContainer;
    //}

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public IntegrationFlow amqpInbound(ConnectionFactory connectionFactory) {
        return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory, "ajk_queue"))
                .transform(new JsonToObjectTransformer(PayloadMessage.class))
                .handle(m -> {
                    System.out.println("##### AMQP Inbound Adapter ####");
                    System.out.println(m.getPayload().toString());
                })
                .get();
    }

}
