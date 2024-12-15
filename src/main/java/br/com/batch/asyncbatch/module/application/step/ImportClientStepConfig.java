package br.com.batch.asyncbatch.module.application.step;

import br.com.batch.asyncbatch.module.domain.model.Person;
import br.com.batch.asyncbatch.module.domain.service.PersonProcessorService;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Configuration
public class ImportClientStepConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public ImportClientStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step StepImportClient(ItemReader<Person> reader, ItemProcessor<Person, Future<Person>> processor,
                                    ItemWriter<Future<Person>> writer) {
        return new StepBuilder("StepImportClient", jobRepository)
                .<Person, Future<Person>>chunk(1000, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
