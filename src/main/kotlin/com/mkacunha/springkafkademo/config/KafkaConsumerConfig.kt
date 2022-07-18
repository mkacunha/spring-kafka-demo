package com.mkacunha.springkafkademo.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory


@EnableKafka
@Configuration
class KafkaConsumerConfig {

    @Value(value = "\${kafka.bootstrap-address}")
    private lateinit var bootstrapAddress: String

    @Value(value = "\${kafka.group-id}")
    private lateinit var groupId: String

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.MAX_POLL_RECORDS_CONFIG] = 1
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = true
//        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
//        props[ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG] = 1500
//        props[ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG] = 300000
//        props[ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG] = 5000
//        props[ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG] = Integer.MAX_VALUE
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}