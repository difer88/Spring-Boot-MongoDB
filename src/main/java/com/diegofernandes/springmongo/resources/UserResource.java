package com.diegofernandes.springmongo.resources;

import com.diegofernandes.springmongo.domain.User;
import com.diegofernandes.springmongo.dto.UserDTO;
import com.diegofernandes.springmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){

        List<User> usersList = service.findAll();

        List<UserDTO> usersListDTO = usersList.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());

        return  ResponseEntity.ok().body(usersListDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id){

        User obj = service.findById(id);

        return  ResponseEntity.ok().body(new UserDTO(obj));
    }

}
