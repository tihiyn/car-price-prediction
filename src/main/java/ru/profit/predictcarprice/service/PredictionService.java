package ru.profit.predictcarprice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.profit.predictcarprice.dao.PredictionHistoryRepository;
import ru.profit.predictcarprice.dao.UserRepository;
import ru.profit.predictcarprice.dao.models.Car;
import ru.profit.predictcarprice.dao.models.PredictionHistory;
import ru.profit.predictcarprice.dao.models.User;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PredictionService {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final PredictionHistoryRepository predictionHistoryRepository;

    @Value("${prediction.api.url}")
    private String apiUrl;

    public int predictPrice(Car car, Authentication authentication) {
        car.setBodyType(car.getBodyType().toLowerCase(Locale.ROOT));

        System.out.println(car.getYearOfProduction());
        System.out.println(car.getCondition());
        System.out.println(car.getGearbox());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Car> request = new HttpEntity<>(car, headers);

        Double predictedPrice = restTemplate.postForObject(
                apiUrl,
                request,
                Double.class
        );

        int roundedPrice = roundUpToThousands(predictedPrice);

        // Сохраняем запрос в историю
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        PredictionHistory history = new PredictionHistory();
        history.setUser(user);
        history.setYearOfProduction(car.getYearOfProduction());
        history.setGearbox(car.getGearbox());
        history.setWheelDrive(car.getWheelDrive());
        history.setMileage(car.getMileage());
        history.setBodyType(car.getBodyType());
        history.setCondition(car.getCondition());
        history.setOwners(car.getOwners());
        history.setEngineVolume(car.getEngineVolume());
        history.setEnginePower(car.getEnginePower());
        history.setFuelType(car.getFuelType());
        history.setPredictedPrice(roundedPrice);
        history.setTimestamp(LocalDateTime.now());
        predictionHistoryRepository.save(history);

        return roundedPrice;
    }

    private int roundUpToThousands(Double number) {
        return (int) Math.ceil(number / 1000) * 1000;
    }
}

