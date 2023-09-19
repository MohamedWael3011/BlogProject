package com.Blog.Project.Blog.controller;

import com.Blog.Project.Blog.exceptions.GeneralException;
import com.Blog.Project.Blog.model.CompositeKeys.Rid;
import com.Blog.Project.Blog.model.React;
import com.Blog.Project.Blog.model.ReturnModels.ReactCount;
import com.Blog.Project.Blog.response.GeneralResponse;
import com.Blog.Project.Blog.service.ReactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api"})
public class ReactController {
    @Autowired
    ReactService reactService;

    @GetMapping("all-react/{pid}")
    public GeneralResponse<?> getReact(@PathVariable("pid") int pid){
        GeneralResponse<List<React>> res = new GeneralResponse<>();
        res.setData(reactService.listReacts(pid));
        res.setSuccess(true);
        return res;
    }
    @GetMapping("count-react/{pid}")
    public GeneralResponse<?> countReact(@PathVariable("pid") int pid){
        GeneralResponse<ReactCount> res = new GeneralResponse<>();
        res.setData(reactService.getReactCount(pid));
        res.setSuccess(true);
        return res;
    }

    @PostMapping("add-react/{uid}")
    public GeneralResponse<?> addReact(@RequestBody React react){
        GeneralResponse<React> res = new GeneralResponse<>();
        res.setData(reactService.addReact(react));
        res.setSuccess(true);
        return res;
    }

    @PutMapping("update-react/{uid}")
    public GeneralResponse<?> editReact(@RequestBody React react) throws GeneralException {
        GeneralResponse<React> res = new GeneralResponse<>();
        res.setData(reactService.editReact(react));
        res.setSuccess(true);
        return res;
    }

    @DeleteMapping("delete-react/{pid}/{uid}")
    public GeneralResponse<?> delReact(@PathVariable("pid") Integer pid, @PathVariable("uid") Integer uid){
        GeneralResponse<React> res = new GeneralResponse<>();
        reactService.delReact(new Rid(pid,uid));
        res.setSuccess(true);
        res.setMessage("React has been deleted");
        return res;
    }

    @GetMapping("all-react/{pid}/{offset}/{pageSize}")
    public ResponseEntity<?> getReactsWithPage(@PathVariable("pid") Integer pid, @PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pagesize) throws GeneralException {
        Page<React> reacts = reactService.getReactsWithPage(pid,offset,pagesize);
        return new ResponseEntity<>(reacts, HttpStatus.OK);
    }

    @GetMapping("react-status/{pid}/{uid}")
    public ResponseEntity<?> getReactsStatus(@PathVariable("pid") Integer pid, @PathVariable("uid") Integer uid){
        return new ResponseEntity<>(reactService.getReactStatus(pid,uid), HttpStatus.OK);
    }
}
