package com.beauty.pro.ia.agendamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoApplication.class, args);
        System.out.println("🚀 BeautyPro IA iniciado com sucesso!");
        System.out.println("🌐 H2 Console: http://localhost:8080/h2-console");
        System.out.println("📝 JDBC URL: jdbc:h2:mem:testdb");
	}

}
