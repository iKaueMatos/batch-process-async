package br.com.batch.asyncbatch.module.application.batch;

import br.com.batch.asyncbatch.module.domain.model.Person;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.Future;

@Configuration
public class ProcessorConfig {
    @Bean
    public ItemProcessor<Person, Future<Person>> asyncProcessor(ItemProcessor<Person, Person> itemProcessor, TaskExecutor taskExecutor) {
        var asyncProcessor = new AsyncItemProcessor<Person, Person>();
        asyncProcessor.setTaskExecutor(taskExecutor);
        asyncProcessor.setDelegate(itemProcessor);
        return asyncProcessor;
    }
}
