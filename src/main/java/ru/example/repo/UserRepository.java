package ru.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
