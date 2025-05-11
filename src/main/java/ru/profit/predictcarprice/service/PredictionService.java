package ru.profit.predictcarprice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.profit.predictcarprice.dao.models.Car;

@Service
@RequiredArgsConstructor
public class PredictionService {
    private final RestTemplate restTemplate;

    @Value("${prediction.api.url}")
    private String apiUrl;

    public double predictPrice(Car car) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Car> request = new HttpEntity<>(car, headers);

        return restTemplate.postForObject(
                apiUrl,
                request,
                Double.class
        );
    }
}