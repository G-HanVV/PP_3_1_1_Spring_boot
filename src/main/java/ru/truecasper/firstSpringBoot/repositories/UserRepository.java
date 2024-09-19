package ru.truecasper.firstSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.truecasper.firstSpringBoot.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
