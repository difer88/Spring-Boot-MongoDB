package com.diegofernandes.springmongo.config;

import com.diegofernandes.springmongo.domain.User;
import com.diegofernandes.springmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User user1 = new User(null, "Charles Lombok", "charleslbk@gmail.com");
        User user2 = new User(null, "Robert Crowley", "robertcr@gmail.com");
        User user3 = new User(null, "Clarice Pattinson", "claricepts@gmail.com");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

    }
}
