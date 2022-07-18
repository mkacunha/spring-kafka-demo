# Demo Spring with Kafka

## commands to run

- UP Kafka: `docker-compose up -d`
- UP API: `mvn spring-boot:run`

## end-point

        curl --location --request POST 'http://localhost:8080/api/messages' \
            --header 'Content-Type: application/json' \
            --data-raw '{
                            "messages": [
                                            "one",
                                            "two",
                                            "three",
                                            "four",
                                            "five"
                                        ]
                        }'

## kafdrop

http://localhost:9000