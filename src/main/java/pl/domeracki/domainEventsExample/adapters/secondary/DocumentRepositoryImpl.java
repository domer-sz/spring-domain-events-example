package pl.domeracki.domainEventsExample.adapters.secondary;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import pl.domeracki.domainEventsExample.domain.CustomDocumentRepository;
import pl.domeracki.domainEventsExample.domain.Document;
import pl.domeracki.domainEventsExample.domain.DocumentEvent;

import java.time.Instant;

@Component
public class DocumentRepositoryImpl implements CustomDocumentRepository {

    private final MongoOperations mongoOperations;
    private final ApplicationEventPublisher applicationEventPublisher;

    public DocumentRepositoryImpl(MongoOperations mongoOperations, ApplicationEventPublisher applicationEventPublisher) {
        this.mongoOperations = mongoOperations;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void customSave(Document document) {
        mongoOperations.save(document);
        applicationEventPublisher.publishEvent(new DocumentEvent(Instant.now(), document));
        document.clearEvents();
    }
}
