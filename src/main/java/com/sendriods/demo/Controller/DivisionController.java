package com.sendriods.demo.Controller;

import com.sendriods.demo.Domain.Division;
import com.sendriods.demo.Service.DivisionService;
import com.sendriods.demo.Service.UserService;
import com.sendriods.demo.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/division")
public class DivisionController {
    // TODO 一般这里用 @Service 注解
    @Resource
    private DivisionService divisionService;
    @Resource
    private UserService userService;

    @PostMapping("/addDivision")
    public Result<Division> addDivision(
            @RequestBody String divisionName,
            @RequestBody Integer divisionId) {
        return Result.success(
                divisionService.addDivision(divisionName, divisionId),
                "succeed!");
/*         TODO 公司的编码风格更喜欢这样子。不起多余的变量
        return Result.success(
                divisionService.addDivision(divisionName, divisionId),
                "succeed!"
        );*/
    }

    // TODO post 虽然时"万能"的，但是 restful 的风格来说，这里应该用 get
    @GetMapping("/getDivisionByName")

    public Result<Division> getDivisionByName(
            @RequestBody String divisionName) {
        return Result.success(
                divisionService.getDivisionByName(divisionName),
                "succeed!");
    }

    // TODO 如果参数 没有 ?id=XXX 是否会报错
    @GetMapping("/getDivisionById")
    public Division getDivisionById(
            @RequestBody long id) {
        return divisionService.getDivisionById(id);
    }

    // TODO 如果参数太多或者json 结构复杂时（比如有多层结构时）考虑新建一个 model 类接收参数
    @PutMapping("/updateDivision")
    public Result<Division> updateDivision(
            @RequestBody Division division
//            @RequestParam Long id,
//            @RequestParam String divisionName,
//            @RequestParam Integer divisionId
    ) {
        divisionService.updateDivision(division);
        return Result.success(division, "succeed!");
    }

    @PostMapping("/addUserToDivision")
    // TODO 注意要养成喜欢，不要丢失类型 Result<Division>
    public Result addUserToDivision(
            @RequestBody Long userId,
            @RequestBody Long divisionId) {
        return Result.success(divisionService.addUserToDivision(
                        divisionService.getDivisionById(divisionId),
                        userService.getUserById(userId)),
                "succeed!");
    }

    // TODO 这个可以理解为对 division 进行修改，可以用 @PutMapping
    @PutMapping("/removeUserToDivision")
    public Result removeUserToDivision(
            @RequestBody Long userId,
            @RequestBody Long divisionId) {
        Division division = divisionService.getDivisionById(divisionId);
        division.removeUser(userService.getUserById(userId));
        return Result.success(
                division,
                "success");
    }

    @GetMapping("/getAllDivision")
    public Result getAllDivision() {
        return Result.success(
                divisionService.getAllDivision(),
                "succeed!");
    }

    // TODO 一般使用 @DeleteMapping
    @DeleteMapping("/deleteDivisionById")
    public Result deleteDivisionById(long id) {
        return Result.success(divisionService.deleteById(id), "succeed!");
    }

}
