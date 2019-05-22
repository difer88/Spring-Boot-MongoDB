package com.diegofernandes.springmongo.resources;

import com.diegofernandes.springmongo.domain.Post;
import com.diegofernandes.springmongo.resources.util.URL;
import com.diegofernandes.springmongo.services.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @ApiOperation(value="Find Post by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value="Find Post by title snippet")
    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return  ResponseEntity.ok().body(list);
    }

    @ApiOperation(value="Find Posts by text and date range")
    @RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "startDate", defaultValue = "") String startDateString,
            @RequestParam(value = "endDate", defaultValue = "") String endDateString){

        text = URL.decodeParam(text);

        Date startDate = URL.convertDate(startDateString, new Date(0L));
        Date endDate = URL.convertDate(endDateString, new Date(0L));

        List<Post> list = service.fullSearch(text, startDate, endDate);

        return ResponseEntity.ok().body(list);

    }

}
