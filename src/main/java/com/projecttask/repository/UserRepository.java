package com.projecttask.repository;

import com.projecttask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUserType(String userType);

    List<User> findByAgeGreaterThanEqual(int age);

    List<User> findByAgeLessThanEqual(int age);

    List<User> findByAgeBetween(int ageGreater, int ageLessThan);

    List<User> findByUserTypeAndAgeBetween(String userType, int ageGreater, int ageLessThan);
}
