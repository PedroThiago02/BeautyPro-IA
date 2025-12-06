package com.beauty.pro.ia.agendamento.services;

import com.beauty.pro.ia.agendamento.dto.LoginDTO;
import com.beauty.pro.ia.agendamento.entities.User;
import com.beauty.pro.ia.agendamento.repositories.UserRepository;
import com.beauty.pro.ia.agendamento.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    @Autowired private UserRepository repo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtService jwt;

    public String register(User user) {

        // Se o role não vier na requisição, define USER como padrão
        if (user.getUserRole() == null) {
            user.setUserRole(2); // <-- padrão 2
        }

        if (user.getUserPlan() == null) {
            user.setUserPlan(1); // <-- padrão 2
        }

        user.setPassword_hash(encoder.encode(user.getPassword_hash()));

        // Define data de criação
        user.setCreated_at(Instant.now());

        repo.save(user);
        return jwt.generateToken(user.getEmail());
    }

    public String login(LoginDTO dto) {
        User user = repo.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(dto.password(), user.getPassword_hash())) {
            throw new RuntimeException("Senha incorreta");
        }

        return jwt.generateToken(user.getEmail());
    }
}
