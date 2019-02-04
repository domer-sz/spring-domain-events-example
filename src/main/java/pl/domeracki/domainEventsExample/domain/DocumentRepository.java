package pl.domeracki.domainEventsExample.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<Document, String>, CustomDocumentRepository {
}


