package pl.domeracki.domainEventsExample.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DocumentEventListener {

    private static final Logger logger = LoggerFactory.getLogger(DocumentEventListener.class);

    @Async("documentListenerThreadPoolTaskExecutor")
    @EventListener
    public void handleDocumentEventThreadPoolTaskExecutor(DocumentEvent event) throws InterruptedException {
        Thread.sleep(100);
        logger.info("ThreadPoolTaskExecutor: " + event);
    }

    @Async("documentListenerThreadPoolExecutor")
    @EventListener
    public void handleDocumentEventThreadPoolExecutor(DocumentEvent event) throws InterruptedException {
        Thread.sleep(150);
        logger.info("ThreadPoolExecutor: " + event);
    }

    @EventListener
    public void handleDocumentEventSync(DocumentEvent event) {
        logger.info("SYNC: " + event);
    }
}
