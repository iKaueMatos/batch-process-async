package br.com.batch.asyncbatch.module.domain.service;

import br.com.batch.asyncbatch.module.domain.model.Person;
import br.com.batch.asyncbatch.module.infraestructure.client.PhotoApiClient;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PersonProcessorService {
    private final PhotoApiClient photoApiClient;

    public PersonProcessorService(PhotoApiClient photoApiClient) {
        this.photoApiClient = photoApiClient;
    }

    @Bean
    public ItemProcessor<Person, Person> process() {
        return Person -> {
            var uri = "https://jsonplaceholder.typicode.com/photos/" + Person.id();
            var newPerson = new Person(Person.id(), Person.name(), Person.email(), Person.datebirth(), Person.age(),
                    Person.thumbnailUrl());
            return newPerson;
        };
    }
}
