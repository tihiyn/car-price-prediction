package ru.profit.predictcarprice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.profit.predictcarprice.dao.models.Car;
import ru.profit.predictcarprice.service.PredictionService;

@Controller
@RequiredArgsConstructor
public class ControllerV1 {
    private final PredictionService predictionService;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("car", new Car());
        return "index";
    }

    @PostMapping("/predict")
    public String predictPrice(@ModelAttribute Car car, Model model) {
        double predictedPrice = predictionService.predictPrice(car);
        model.addAttribute("predictedPrice", predictedPrice);
        model.addAttribute("car", car);
        return "result";
    }
}
