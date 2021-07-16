package com.talents.orange.marvelapi.repository;

import com.talents.orange.marvelapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email like ?1")
    Optional<User> findUserByEmail(String email);

    @Query("select u from User u where u.cpf like ?1")
    Optional<User> findUserByCpf(String cpf);

}
