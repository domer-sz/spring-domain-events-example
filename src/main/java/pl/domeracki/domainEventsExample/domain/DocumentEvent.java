package pl.domeracki.domainEventsExample.domain;

import java.time.Instant;

public class DocumentEvent {
    private final Instant timestamp;
    private final Document document;

    public DocumentEvent(Instant timestamp, Document document) {
        this.timestamp = timestamp;
        this.document = document;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DocumentEvent{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", document=").append(document);
        sb.append('}');
        return sb.toString();
    }
}
