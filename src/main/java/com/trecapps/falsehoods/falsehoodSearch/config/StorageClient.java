package com.trecapps.falsehoods.falsehoodSearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class StorageClient {

    RestTemplate client;

    public StorageClient()
    {
        client = new RestTemplate();
    }

    @Value("${storage.url}")
    String baseStorageUrl;

    public ResponseEntity<String> getContents(String id, String app)
    {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("FileId", id);
        headers.add("App", app);
        return client.exchange(baseStorageUrl + "/download", HttpMethod.GET,new HttpEntity(headers),String.class);
    }

}
