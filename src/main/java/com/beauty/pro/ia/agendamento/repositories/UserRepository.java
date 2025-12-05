package com.beauty.pro.ia.agendamento.repositories;

import com.beauty.pro.ia.agendamento.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
