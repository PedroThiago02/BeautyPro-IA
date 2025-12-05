package com.beauty.pro.ia.agendamento.config;

import com.beauty.pro.ia.agendamento.entities.User;
import com.beauty.pro.ia.agendamento.enums.UserPlan;
import com.beauty.pro.ia.agendamento.enums.UserRole;
import com.beauty.pro.ia.agendamento.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Pedro Thiago", "pedroth987@gmail.com", "123456", "70609548484", UserRole.ADMIN, UserPlan.PREMIUM, Instant.parse("2019-06-20T19:53:07Z"));

        userRepository.save(u1);

    }

}
