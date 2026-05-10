package com.example.newMock1.Consumer;

import com.example.newMock1.Entity.MessageEntity;
import com.example.newMock1.Model.KafkaMessageDTO;
import com.example.newMock1.Repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(MessageConsumer.class);

    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;

    public MessageConsumer(MessageRepository messageRepository,
                           ObjectMapper objectMapper) {

        this.messageRepository = messageRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "test1", groupId = "test1")
    public void consumeMessage(String message) {

        try {
            log.info("Kafka message received: {}", message);

            KafkaMessageDTO dto =
                    objectMapper.readValue(message, KafkaMessageDTO.class);

            MessageEntity entity = new MessageEntity();

            entity.setMsgId(dto.getMsg_id());
            entity.setInn(dto.getInn());
            entity.setFullName(dto.getFull_name());
            entity.setTime(LocalDateTime.now());

            messageRepository.save(entity);

            log.info("Message saved to DB: {}", entity.getMsgId());

        } catch (Exception e) {

            log.error("Error processing kafka message", e);
        }
    }
}