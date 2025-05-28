package ru.profit.predictcarprice.dao.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.profit.predictcarprice.enums.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "prediction_history")
@Getter
@Setter
public class PredictionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int yearOfProduction;

    @Enumerated(EnumType.STRING)
    private Gearbox gearbox;

    @Enumerated(EnumType.STRING)
    private WheelDrive wheelDrive;

    private int mileage;
    private String bodyType;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Enumerated(EnumType.STRING)
    private Owners owners;

    private double engineVolume;
    private int enginePower;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(nullable = false)
    private int predictedPrice;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}

