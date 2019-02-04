package com.example.demo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CustomDocumentRepositoryImpl implements CustomDocumentRepository {

    private final MongoOperations mongoOperations;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CustomDocumentRepositoryImpl(MongoOperations mongoOperations, ApplicationEventPublisher applicationEventPublisher) {
        this.mongoOperations = mongoOperations;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void customSave(Document document) {
        mongoOperations.save(document);
        applicationEventPublisher.publishEvent(new DocumentEvent(Instant.now(), null));
        document.clearEvents();
    }
}
