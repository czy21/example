package com.czy.pulsar.annotation;

import org.apache.pulsar.client.api.SubscriptionInitialPosition;
import org.apache.pulsar.client.api.SubscriptionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PulsarListener {
    String topic();

    Class<?> clazz() default byte[].class;

    Serialization serialization() default Serialization.JSON;

    SubscriptionType subscriptionType() default SubscriptionType.Exclusive;

    String consumerName() default "";

    String subscriptionName() default "";

    enum Serialization {
        JSON,
        AVRO,
        STRING,
        BYTE,
        PROTOBUF
    }
}
