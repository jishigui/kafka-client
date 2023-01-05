package com.yix.simple;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class HelloKafkaProducer {

    public static void main(String[] args) {
        // 设置属性
        Properties properties = new Properties();
        // 指定连接的kafka服务器的地址 配置多台服务器，用,分割，其中一个宕机，生产者依然可以连上(集群)
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        // 设置string的序列化(对象 -> 二进制字节数组 能够再网络上传输)
        properties.put("key.serializer", StringSerializer.class);
        properties.put("value.serializer", StringSerializer.class);

        // 构建kafka生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        try {
            ProducerRecord<String, String> record;
            try {
                // 构建消息
                record = new ProducerRecord<String, String>("yix", "language", "Java");
                // 发送消息
                producer.send(record);
                System.out.println("message is sent");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            // 释放了解
            producer.close();
        }

    }
}
