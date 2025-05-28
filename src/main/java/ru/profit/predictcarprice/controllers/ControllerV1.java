package ru.profit.predictcarprice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.profit.predictcarprice.dao.PredictionHistoryRepository;
import ru.profit.predictcarprice.dao.UserRepository;
import ru.profit.predictcarprice.dao.models.Car;
import ru.profit.predictcarprice.dao.models.PredictionHistory;
import ru.profit.predictcarprice.dao.models.User;
import ru.profit.predictcarprice.service.PredictionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ControllerV1 {
    private final PredictionService predictionService;
    private final UserRepository userRepository;
    private final PredictionHistoryRepository predictionHistoryRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("car", new Car());
        return "index";
    }

    @PostMapping("/predict")
    public String predictPrice(@ModelAttribute Car car, Model model, Authentication authentication) {
        int predictedPrice = predictionService.predictPrice(car, authentication);
        model.addAttribute("predictedPrice", predictedPrice);
        model.addAttribute("car", car);
        return "result";
    }

    @GetMapping("/account")
    public String account(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<PredictionHistory> history = predictionHistoryRepository
                .findByUserOrderByTimestampDesc(user);

        model.addAttribute("history", history);
        return "profile";
    }
}
