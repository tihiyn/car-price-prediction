package ru.profit.predictcarprice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.profit.predictcarprice.dao.models.PredictionHistory;
import ru.profit.predictcarprice.dao.models.User;

import java.util.List;

public interface PredictionHistoryRepository extends JpaRepository<PredictionHistory, Long> {
    List<PredictionHistory> findByUserOrderByTimestampDesc(User user);
}
