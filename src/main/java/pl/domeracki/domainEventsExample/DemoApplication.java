package pl.domeracki.domainEventsExample;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pl.domeracki.domainEventsExample.domain.Document;
import pl.domeracki.domainEventsExample.domain.DocumentRepository;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        final DocumentRepository repository = applicationContext.getBean(DocumentRepository.class);
        repository.save(new Document(UUID.randomUUID().toString(), "TEST"));
        repository.customSave(new Document(UUID.randomUUID().toString(), "Own Impl"));
        logger.info("koniec programu");

    }

    @Bean(name = "documentListenerThreadPoolTaskExecutor")
    public Executor getDocumentListenerThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(100);
        executor.initialize();
        return executor;
    }


    @Bean(name = "documentListenerThreadPoolExecutor")
    public Executor getDocumentListenerThreadPooExecutor() {
        return new ThreadPoolExecutor(
            1,
            100,
            0,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue(100),
            new ThreadFactoryBuilder()
                .setNameFormat("MyPool-%d")
                .build(),
            (runnable, executor) -> logger.error("NotificationBus cannot accept more tasks, work queue(size=${executor.queue.size} was exceeded) ")
        );
    }
}

