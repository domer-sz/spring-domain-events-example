package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

@org.springframework.data.mongodb.core.mapping.Document("TestDocument")
public class Document {

    @Transient
    private final Logger logger = LoggerFactory.getLogger(Document.class);

    @Id
    private final String id;
    private final String name;

    @Transient
    private final Collection<DocumentEvent> domainEvents = new ArrayList<>();

    public Document(String id, String name) {
        this.id = id;
        this.name = name;
        domainEvents.add(new DocumentEvent(Instant.now(), this));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @DomainEvents
    public Collection<DocumentEvent> events() {
        return domainEvents;
    }

    @AfterDomainEventPublication
    public void clearEvents() {
        logger.info("CLEAR EVENTS");
        domainEvents.clear();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Document{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
