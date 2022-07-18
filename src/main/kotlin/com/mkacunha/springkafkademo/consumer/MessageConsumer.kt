package com.mkacunha.springkafkademo.consumer

import com.mkacunha.springkafkademo.config.TOPIC_ONE
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
class MessageConsumer {

    @KafkaListener(topics = [TOPIC_ONE])
    fun listenGroupFoo(message: String) {
        if (message == "two") {
            throw RuntimeException("Two error")
        }

        println("===> Received Message: $message")
    }
}