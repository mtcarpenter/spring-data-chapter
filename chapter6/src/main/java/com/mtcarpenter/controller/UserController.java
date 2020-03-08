package com.mtcarpenter.controller;

import com.mtcarpenter.entity.User;
import com.mtcarpenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 山间木匠
 * @Date 2020/3/8
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("userList", userService.list());
        return "list";
    }


    @GetMapping("/toAdd")
    public String toAdd(){
        return "add";
    }


    @GetMapping("/toUpdate")
    public String toUpdate(Long id,Model model){
        model.addAttribute("user", userService.findById(id));
        return "update";
    }

    @PostMapping("/add")
    public String add(User user){
        userService.save(user);
        return "redirect:/user/list";
    }

    @PostMapping("/update")
    public String update(User user){
        userService.save(user);
        return "redirect:/user/list";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        userService.deleteById(id);
        return "redirect:/user/list";
    }

}
