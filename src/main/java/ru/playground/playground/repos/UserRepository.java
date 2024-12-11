package ru.playground.playground.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.playground.playground.entities.PgUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<PgUser, Long> {
    Optional<PgUser> findByUsername(String username);
}