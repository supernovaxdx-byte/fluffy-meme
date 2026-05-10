package com.example.newMock1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "msg_id")
    private String msgId;

    @Column(name = "inn")
    private String inn;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "time")
    private LocalDateTime time;
}