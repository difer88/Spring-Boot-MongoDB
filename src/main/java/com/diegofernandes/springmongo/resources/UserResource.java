package com.diegofernandes.springmongo.resources;

import com.diegofernandes.springmongo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll(){

        User user1 = new User("1", "User_01", "user_01@gmail.com");
        User user2 = new User("2", "User_02", "user_02@gmail.com");

        List usersList = new ArrayList();

        usersList.addAll(Arrays.asList(user1, user2));

        return  ResponseEntity.ok().body(usersList);
    }

}
