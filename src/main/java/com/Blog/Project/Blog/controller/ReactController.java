package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.CompositeKeys.Rid;
import com.Blog.Project.Blog.model.React;
import com.Blog.Project.Blog.service.ReactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/react"})
public class ReactController {
    @Autowired
    ReactService reactService;

    @GetMapping("{pid}")
    public ResponseEntity<?> getReact(@PathVariable("pid") int pid){
        return new ResponseEntity<>(reactService.listReacts(pid), HttpStatus.OK);
    }
    @GetMapping("{pid}/count")
    public ResponseEntity<?> countReact(@PathVariable("pid") int pid){
        return new ResponseEntity<>(reactService.getReactCount(pid),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addReact(@RequestBody React react){
        return new ResponseEntity<>(reactService.addReact(react),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editReact(@RequestBody React react) throws GeneralException {
        return new ResponseEntity<>(reactService.editReact(react),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delReact(@RequestBody Rid rid){
        reactService.delReact(rid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{pid}/{offset}/{pageSize}")
    public ResponseEntity<?> getReactsWithPage(@PathVariable("pid") Integer pid, @PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pagesize) throws GeneralException {
        Page<React> reacts = reactService.getReactsWithPage(pid,offset,pagesize);
        return new ResponseEntity<>(reacts, HttpStatus.OK);
    }

    @GetMapping("/{pid}/{uid}")
    public ResponseEntity<?> getReactsStatus(@PathVariable("pid") Integer pid, @PathVariable("uid") Integer uid){
        return new ResponseEntity<>(reactService.getReactStatus(pid,uid), HttpStatus.OK);
    }
}
