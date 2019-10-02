package io.ashimjk.kafkacloudstream.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Processor.class)
@SpringBootApplication
@Slf4j
public class KafkaCloudStreamProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaCloudStreamProducerApplication.class, args);
    }

    @RestController
    @RequiredArgsConstructor
    static class MessageHandler {

        private final Source source;

        @GetMapping
        public String handle(@RequestParam String value) {
            LOG.info("Inside MessageHandler.handle()");

            source.output().send(MessageBuilder.withPayload(value).build());

            return value;
        }
    }

}
