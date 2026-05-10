package com.example.newMock1.Controller;

import com.example.newMock1.Model.KafkaMessageDTO;
import com.example.newMock1.Producer.MessageProducer;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private static final Logger log =
            LoggerFactory.getLogger(MainController.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final MessageProducer messageProducer;

    public MainController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping(
            value = "/post-message",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> postMessage(
            @RequestBody KafkaMessageDTO requestDTO) {

        try {

            String json =
                    mapper.writeValueAsString(requestDTO);

            log.info("RequestDTO: {}", json);

            messageProducer.sendMessage("test1", json);

            return ResponseEntity.ok(requestDTO);

        } catch (Exception e) {

            log.error("Error processing request", e);

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}