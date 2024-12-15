package br.com.batch.asyncbatch.module.application.batch;

import br.com.batch.asyncbatch.module.domain.model.Person;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ReaderConfig {
    @Bean
    public ItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("PersonsFileReader")
                .resource(new FileSystemResource("files/Persons.csv"))
                .delimited()
                .names("nome", "email", "dataNascimento", "idade", "id")
                .addComment("--")
                .fieldSetMapper((FieldSet fieldSet) -> {
                    return new Person(fieldSet.readLong("id"),
                            fieldSet.readString("nome"), fieldSet.readString("email"),
                            fieldSet.readString("dataNascimento"), fieldSet.readInt("idade"), null);
                })
                .build();
    }
}
