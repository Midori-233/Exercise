package com.sendriods.demo.Controller;

import com.sendriods.demo.Domain.Division;
import com.sendriods.demo.Service.DivisionService;
import com.sendriods.demo.Service.UserService;
import com.sendriods.demo.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/division")
public class DivisionController {
    final private DivisionService divisionService;
    final private UserService userService;

    public DivisionController(DivisionService divisionService, UserService userService) {
        this.divisionService = divisionService;
        this.userService = userService;
    }

    @PostMapping("/addDivision")
    public Result<Division> addDivision(
            @RequestBody Division division) {
        return Result.success(
                divisionService.addDivision(division),
                "succeed!");
    }

    //  post 虽然时"万能"的，但是 restful 的风格来说，这里应该用 get
    @GetMapping("/getDivisionByName")

    public Result getDivisionByName(
            @RequestParam Optional<String> divisionName) {
        return divisionName.map(
                i -> Result.success(
                        divisionService.getDivisionByName(divisionName.get()),
                        "succeed!")
        ).orElse(Result.error("234", "No Valid Input"));
    }

    //  如果参数 没有 ?id=XXX 是否会报错
    @GetMapping("/getDivisionById")
    public Result getDivisionById(
            @RequestParam Optional<Long> id) {
        return id.map(
                i -> Result.success(divisionService.getDivisionById(id.get()), "succeed!")
        ).orElse(Result.error("234", "No Valid Input"));
    }

    // TODO 如果参数太多或者json 结构复杂时（比如有多层结构时）考虑新建一个 model 类接收参数
    @PutMapping("/updateDivision")
    public Result<Division> updateDivision(
            @RequestBody Division division) {
        return Result.success(
                divisionService.updateDivision(division), "succeed!");
    }

    @PostMapping("/addUserToDivision")
    public Result addUserToDivision(
            @RequestBody Long userId,
            @RequestBody Long divisionId) {
        return Result.success(divisionService.addUserToDivision(
                        divisionService.getDivisionById(divisionId),
                        userService.getUserById(userId)),
                "succeed!");
    }

    @PutMapping("/removeUserToDivision")
    public Result removeUserToDivision(
            @RequestBody Long userId,
            @RequestBody Long divisionId) {
        Division division = divisionService.getDivisionById(divisionId);
        division.removeUser(userService.getUserById(userId));
        return Result.success(division, "success");
    }

    @GetMapping("/getAllDivision")
    public Result getAllDivision() {
        return Result.success(divisionService.getAllDivision(), "succeed!");
    }

    @DeleteMapping("/deleteDivisionById")
    public Result deleteDivisionById(long id) {
        return Result.success(divisionService.deleteById(id), "succeed!");
    }

}
