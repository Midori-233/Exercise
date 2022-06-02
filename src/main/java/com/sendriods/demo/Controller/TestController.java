package com.sendriods.demo.Controller;

import com.sendriods.demo.Domain.User;
import com.sendriods.demo.Service.DivisionService;
import com.sendriods.demo.Service.UserService;
import com.sendriods.demo.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    final private UserService userService;

    final private DivisionService divisionService;

    public TestController(UserService userService, DivisionService divisionService) {
        this.userService = userService;
        this.divisionService = divisionService;
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    /*    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")*/
    //FIXME
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/getAllUser")
/*
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
*/
    public Result getAllUser() {
        List<User> userList = userService.getAllUser();
        System.out.println(userList);
        return Result.success(userList, "succeed!");
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
