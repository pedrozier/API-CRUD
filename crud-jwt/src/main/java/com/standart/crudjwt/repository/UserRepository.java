package com.standart.crudjwt.repository;

import java.util.Optional;

import com.standart.crudjwt.model.MyUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    public Optional<MyUser> findByUserName(String userName);

}
