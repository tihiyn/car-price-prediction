package ru.profit.predictcarprice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.profit.predictcarprice.dao.models.Car;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PredictionService {
    private final RestTemplate restTemplate;

    @Value("${prediction.api.url}")
    private String apiUrl;

    public int predictPrice(Car car) {
        car.setBodyType(car.getBodyType().toLowerCase(Locale.ROOT));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Car> request = new HttpEntity<>(car, headers);

        Double predictedPrice = restTemplate.postForObject(
                apiUrl,
                request,
                Double.class
        );

        return roundUpToThousands(predictedPrice);
    }

    private int roundUpToThousands(Double number) {
        return (int) Math.ceil(number / 1000) * 1000;
    }
}

