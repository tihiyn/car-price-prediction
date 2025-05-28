package ru.profit.predictcarprice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.profit.predictcarprice.dao.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
