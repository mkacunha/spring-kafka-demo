package com.mkacunha.springkafkademo.controller

import com.fasterxml.jackson.annotation.JsonProperty
import com.mkacunha.springkafkademo.config.TOPIC_ONE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class MessageController {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    @PostMapping("/api/messages")
    fun post(@RequestBody body: MessageRequest) {
        body.messages.forEach { kafkaTemplate.send(TOPIC_ONE, it) }
    }

    class MessageRequest(
        @JsonProperty("messages")
        val messages: List<String>
    )
}