package br.com.batch.asyncbatch.module.application.job;

import br.com.batch.asyncbatch.module.application.step.ImportClientStepConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportClientJobConfig {
    private final JobRepository jobRepository;
    private final ImportClientStepConfig importClientsStepConfig;

    public ImportClientJobConfig(JobRepository jobRepository, ImportClientStepConfig importClientsStepConfig) {
        this.jobRepository = jobRepository;
        this.importClientsStepConfig = importClientsStepConfig;
    }

    @Bean
    public Job TaskImportClientJob(JobRepository jobRepository, Step importarClientesStep) {
        return new JobBuilder("TaskImportClientJob", jobRepository)
                .start(importarClientesStep)
                .build();
    }
}
