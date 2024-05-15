package com.tivic.file_manager.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public FileService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String strFile){
        this.kafkaTemplate.send(topic, strFile);
    }
}
