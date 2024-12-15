package br.com.batch.asyncbatch.module.infraestructure.client;

import br.com.batch.asyncbatch.module.domain.model.Photo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PhotoApiClient {
    private final RestTemplate restTemplate;

    public PhotoApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Photo getPhotoById(Long id) {
        String uri = "https://jsonplaceholder.typicode.com/photos/" + id;
        return restTemplate.getForObject(uri, Photo.class);
    }
}
