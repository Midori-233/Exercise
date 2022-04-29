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
    // TODO 一般这里用 @Service 注解
    @Resource
    private DivisionService divisionService;
    @Resource
    private UserService userService;

    @PostMapping("/addDivision")
    public Result<Division> addDivision(@RequestParam String divisionName, @RequestParam Integer divisionId) {
        Division division = divisionService.addDivision(divisionName, divisionId);
        return Result.success(division, "success!");
        // TODO 公司的编码风格更喜欢这样子。不起多余的变量
//        return Result.success(
//                divisionService.addDivision(divisionName, divisionId),
//                "succeed!"
//        );
    }

    // TODO post 虽然时"万能"的，但是 restful 的风格来说，这里应该用 get
    @PostMapping("/getDivisionByName")
    public Result<Division> getDivisionByName(String divisionName) {
        Division division = divisionService.getDivisionByName(divisionName);
        return Result.success(division, "succeed!");
    }

    // TODO 如果参数 没有 ?id=XXX 是否会报错
    @PostMapping("/getDivisionById")
    public Division getDivisionById(@RequestParam long id) {
        Division division = divisionService.getDivisionById(id);
        return division;
    }

    // TODO 如果参数太多或者json 结构复杂时（比如有多层结构时）考虑新建一个 model 类接收参数
    @PostMapping("/updateDivision")
    public Result<Division> updateDivision(@RequestParam Long id, @RequestParam String divisionName, @RequestParam Integer divisionId) {
        Division division = divisionService.updateDivision(id, divisionName, divisionId);
        return Result.success(division, "succeed!");
    }

    @PostMapping("/addUserToDivision")
    // TODO 注意要养成喜欢，不要丢失类型 Result<Division>
    public Result addUserToDivision(@RequestParam Long userId, @RequestParam Long divisionId) {
        User user = userService.getUserById(userId);
        Division division = divisionService.getDivisionById(divisionId);
        return Result.success(divisionService.addUserToDivision(division, user), "succeed!");
    }

    // TODO 这个可以理解为对 division 进行修改，可以用 @PutMapping
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

    // TODO 一般使用 @DeleteMapping
    @PostMapping("/deleteDivisionById")
    public Result deleteDivisionById(long id) {
        Division division = divisionService.deleteDivisionById(id);
        return Result.success(division, "succeed!");
    }

}
