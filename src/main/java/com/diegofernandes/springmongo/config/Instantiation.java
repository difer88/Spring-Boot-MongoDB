package com.diegofernandes.springmongo.config;

import com.diegofernandes.springmongo.domain.Post;
import com.diegofernandes.springmongo.domain.User;
import com.diegofernandes.springmongo.dto.AuthorDTO;
import com.diegofernandes.springmongo.dto.CommentDTO;
import com.diegofernandes.springmongo.repository.PostRepository;
import com.diegofernandes.springmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

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

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

        Post post1 = new Post(null, sdf.parse("14/08/2019"), "Going to travel",
                "I'm getting ready to travel to London !", new AuthorDTO(user1));

        Post post2 = new Post(null, sdf.parse("19/08/2019"), "Travelling again",
                "Now leaving London to go to Paris !", new AuthorDTO(user1));

        Post post3 = new Post(null, sdf.parse("26/10/2019"), "Disease prevention",
                "Always remember to wash your hands !", new AuthorDTO(user2));

        Post post4 = new Post(null, sdf.parse("05/02/2019"), "Morning walk",
                "I'm going to walk in the park with my dog !", new AuthorDTO(user3));

        CommentDTO comment1 = new CommentDTO("Awesome !", sdf.parse("14/08/2019"), new AuthorDTO(user2));
        CommentDTO comment2 = new CommentDTO("Nice...enjoy !", sdf.parse("14/08/2019"), new AuthorDTO(user3));
        CommentDTO comment3 = new CommentDTO("Send photos !", sdf.parse("20/08/2019"), new AuthorDTO(user3));
        CommentDTO comment4 = new CommentDTO("It's really important !", sdf.parse("26/10/2019"), new AuthorDTO(user1));
        CommentDTO comment5 = new CommentDTO("I never forget...", sdf.parse("28/10/2019"), new AuthorDTO(user3));
        CommentDTO comment6 = new CommentDTO("Don't forget jour sweather !", sdf.parse("05/02/2019"), new AuthorDTO(user2));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().add(comment3);
        post3.getComments().addAll(Arrays.asList(comment4, comment5));
        post4.getComments().add(comment6);

        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));

        user1.getPosts().addAll(Arrays.asList(post1, post2));
        user2.getPosts().add(post3);
        user3.getPosts().add(post4);

        userRepository.saveAll(Arrays.asList(user1, user2, user3));

    }
}
