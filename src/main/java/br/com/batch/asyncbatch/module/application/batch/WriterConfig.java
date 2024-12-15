package br.com.batch.asyncbatch.module.application.batch;

import br.com.batch.asyncbatch.module.domain.model.Person;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Future;

@Configuration
public class WriterConfig {
    @Bean
    public ItemWriter<Future<Person>> asyncWriter(ItemWriter<Person> writer) {
        var asyncWriter = new AsyncItemWriter<Person>();
        asyncWriter.setDelegate(writer);
        return asyncWriter;
    }

    @Bean
    public ItemWriter<Person> writer() {
        return System.out::println;
    }
}
