package ru.example.mealapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.mealapi.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
