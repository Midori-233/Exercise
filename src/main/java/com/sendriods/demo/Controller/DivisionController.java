package com.sendriods.demo.Controller;

import com.sendriods.demo.Domain.Division;
import com.sendriods.demo.Domain.User;
import com.sendriods.demo.Service.DivisionService;
import com.sendriods.demo.Service.UserService;
import com.sendriods.demo.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/division")
public class DivisionController {
    @Resource
    private DivisionService divisionService;
    @Resource
    private UserService userService;

    @PostMapping("/addDivision")
    public Result<Division> addDivision(@RequestParam String divisionName, @RequestParam Integer divisionId) {
        Division division = divisionService.addDivision(divisionName, divisionId);
        return Result.success(division, "success!");
    }

    @PostMapping("/getDivisionByName")
    public Result<Division> getDivisionByName(String divisionName) {
        Division division = divisionService.getDivisionByName(divisionName);
        return Result.success(division, "succeed!");
    }

    @PostMapping("/getDivisionById")
    public Division getDivisionById(@RequestParam long id) {
        Division division = divisionService.getDivisionById(id);
        return division;
    }

    @PostMapping("/updateDivision")
    public Result<Division> updateDivision(@RequestParam Long id, @RequestParam String divisionName, @RequestParam Integer divisionId) {
        Division division = divisionService.updateDivision(id, divisionName, divisionId);
        return Result.success(division, "succeed!");
    }

    @PostMapping("/addUserToDivision")
    public Result addUserToDivision(@RequestParam Long userId, @RequestParam Long divisionId) {
        User user = userService.getUserById(userId);
        Division division = divisionService.getDivisionById(divisionId);
        return Result.success(divisionService.addUserToDivision(division, user), "succeed!");
    }

    @PostMapping("/removeUserToDivision")
    public Division removeUserToDivision(@RequestParam Long userId, @RequestParam Long divisionId) {
        User user = userService.getUserById(userId);
        Division division = divisionService.getDivisionById(divisionId);
        division.removeUser(user);
        return division;
    }

    @PostMapping("/getAllDivision")
    public Result getAllDivision() {
        List<Division> divisionList = divisionService.getAllDivision();
        return Result.success(divisionList, "succeed!");
    }

    @PostMapping("/deleteDivisionById")
    public Result deleteDivisionById(long id) {
        Division division = divisionService.deleteDivisionById(id);
        return Result.success(division, "succeed!");
    }

}
