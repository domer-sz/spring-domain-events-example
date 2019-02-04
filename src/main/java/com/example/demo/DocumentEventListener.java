package com.example.demo;

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
    public void handleDocumentEvent(DocumentEvent event) {
        logger.info("ThreadPoolTaskExecutor: " + event);
    }

    @Async("documentListenerThreadPoolExecutor")
    @EventListener
    public void handleDocumentEvent2(DocumentEvent event) {
        logger.info("ThreadPoolExecutor: " + event);
    }
}
