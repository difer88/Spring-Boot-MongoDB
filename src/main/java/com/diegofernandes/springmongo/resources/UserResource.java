package com.diegofernandes.springmongo.resources;

import com.diegofernandes.springmongo.domain.Post;
import com.diegofernandes.springmongo.domain.User;
import com.diegofernandes.springmongo.dto.UserDTO;
import com.diegofernandes.springmongo.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;

    @ApiOperation(value="List all Users")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){

        List<User> usersList = service.findAll();

        List<UserDTO> usersListDTO = usersList.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());

        return  ResponseEntity.ok().body(usersListDTO);
    }

    @ApiOperation(value="Find User by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id){

        User obj = service.findById(id);

        return  ResponseEntity.ok().body(new UserDTO(obj));
    }

    @ApiOperation(value="Create new User")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO){

        User obj = service.fromDTO(objDTO);
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value="Delete User by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){

        service.delete(id);

        return  ResponseEntity.noContent().build();
    }

    @ApiOperation(value="Update User by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id){

        User obj = service.fromDTO(objDTO);
        obj.setId(id);
        service.update(obj);

        return  ResponseEntity.noContent().build();

    }

    @ApiOperation(value="Find Posts by User ID")
    @RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){

        User obj = service.findById(id);

        return  ResponseEntity.ok().body(obj.getPosts());
    }

}
