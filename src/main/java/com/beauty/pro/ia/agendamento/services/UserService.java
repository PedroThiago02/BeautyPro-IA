package com.beauty.pro.ia.agendamento.services;

import com.beauty.pro.ia.agendamento.entities.User;
import com.beauty.pro.ia.agendamento.repositories.UserRepository;
import com.beauty.pro.ia.agendamento.services.exceptions.DatabaseException;
import com.beauty.pro.ia.agendamento.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User user) {

        // Aplica hash na senha ANTES de salvar
        if (user.getPassword_hash() != null && !user.getPassword_hash().isBlank()) {
            user.setPassword_hash(passwordEncoder.encode(user.getPassword_hash()));
        }

        // Define data de criação
        user.setCreated_at(Instant.now());

        return userRepository.save(user);
    }

    public void delete(Long id) {

        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User body) {
        try {
            User entity = userRepository.getReferenceById(id);
            updateData(entity, body);
            return userRepository.save(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User body) {

        entity.setName(body.getName());
        entity.setEmail(body.getEmail());
        entity.setCpf(body.getCpf());
        entity.setUserPlan(body.getUserPlan());
        entity.setUserRole(body.getUserRole());

        // atualiza senha APENAS se enviada
        if (body.getPassword_hash() != null && !body.getPassword_hash().isBlank()) {
            entity.setPassword_hash(passwordEncoder.encode(body.getPassword_hash()));
        }
    }
}