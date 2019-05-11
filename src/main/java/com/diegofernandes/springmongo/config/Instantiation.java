package com.diegofernandes.springmongo.config;

import com.diegofernandes.springmongo.domain.Post;
import com.diegofernandes.springmongo.domain.User;
import com.diegofernandes.springmongo.repository.PostRepository;
import com.diegofernandes.springmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.aggregation.DateOperators;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

/**
 * Class responsible for automatically instantiating mass of data for
 * tests when initializing the application.
 *
 * @author  Diego Fernandes
 * @version 1.0.1
 * @since   2019-04-23
 */
@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User user1 = new User(null, "Charles Lombok", "charleslbk@gmail.com");
        User user2 = new User(null, "Robert Crowley", "robertcr@gmail.com");
        User user3 = new User(null, "Clarice Pattinson", "claricepts@gmail.com");

        Post post1 = new Post(null, sdf.parse("14/08/2019"), "Going to travel",
                "I'm getting ready to travel to London !", user1);

        Post post2 = new Post(null, sdf.parse("26/10/2019"), "Disease prevention",
                "Always remember to wash your hands !", user2);

        Post post3 = new Post(null, sdf.parse("05/02/2019"), "Morning walk",
                "I'm going to walk in the park with my dog !", user3);

        userRepository.saveAll(Arrays.asList(user1, user2, user3));
        postRepository.saveAll(Arrays.asList(post1, post2));

    }
}
