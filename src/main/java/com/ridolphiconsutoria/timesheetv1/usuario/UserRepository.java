package com.ridolphiconsutoria.timesheetv1.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, String> {


    Optional<UserModel> findByUsername(String username);
}
