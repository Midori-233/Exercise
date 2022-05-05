package com.sendriods.demo.Controller;

import com.sendriods.demo.Domain.Division;
import com.sendriods.demo.Domain.User;
import com.sendriods.demo.Service.DivisionService;
import com.sendriods.demo.Service.UserService;
import com.sendriods.demo.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private DivisionService divisionService;

    @PostMapping("/addUser")
    public Result<User> addUser(
            @RequestParam String name,
            @RequestParam Integer age,
            @RequestParam String passwd) {
        // TODO jpa save/update 之后是不会返回 null 的，如果发生错误的话会抛出异常而不会返回 null
        try {
            User user = userService.addUser(name, age, passwd);
            return Result.success(user, "注册成功！");
        } catch (Exception exception) {
            return Result.error(exception.getMessage(), "注册失败！");
        }
    }

    // TODO update 使用 @PutMapping
    @PutMapping("/updateUser")
    public Result<User> updateUser(@RequestParam long id, @RequestParam String name, @RequestParam Integer age, @RequestParam String passwd) {
        try {
            User user = userService.updateUser(id, name, age, passwd);
            return Result.success(user, "更新成功！");
        } catch (Exception exception) {
            return Result.error(exception.getMessage(), "更新失败！");
        }
    }

    @PostMapping("/getUserById")
    public Result<User> getUserById(@RequestParam Optional<Long> id) {
        // TODO 对于可能为空的类型，建议使用 Optional
        //  参考 https://github.com/100TB/OnJava8/blob/master/docs/book/14-Streams.md#optional%E7%B1%BB
        //  顺便可以看看 Stream 的 API
        if (id.isPresent())
            return Result.success(userService.getUserById(id.get().longValue()), "OK");
        else
            return Result.error("234", "No Value Input");
    }

    @PostMapping("/getUserByName")
    public Result<User> getUserByName(@RequestParam String name) {
        User user = userService.getUserByName(name);
        if (user != null) {
            return Result.success(user, "OK");
        } else {
            return Result.error("233", "N");
        }
    }

    @PostMapping("/getAllUser")
    public Result getAllUser() {
        List<User> userList = userService.getAllUser();
        return Result.success(userList, "succeed!");
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/deleteByName")
    public Result deleteUserByName(@RequestParam String name) {
        userService.deleteUserByName(name);
        return Result.success("OK!");
    }

    @PostMapping("/deleteById")
    public Result deleteUserById(@RequestParam long id) {
        userService.deleteUserById(id);
        return Result.success("OK!");
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(User user) {
        userService.deleteUser(user);
        return Result.success("OK!");
    }

    @PostMapping("/getAllPage")
    public Result getAllPage(@RequestParam int pageNum, @RequestParam int pageSize) {
        Page<User> userPage = userService.paging(pageNum, pageSize);
        return Result.success(userPage);
    }

    @PostMapping("/getByAgeLessThanPage")
    public Result getByAgeLessThanPage(@RequestParam int age, @RequestParam int pageNum, @RequestParam int pageSize) {
        Page<User> userPage = userService.findByAgeLessThanPage(age, pageNum, pageSize);
        return Result.success(userPage);
    }

    @PostMapping("/setDivisionToUser")
    public Result setDivisionToUser(@RequestParam long divisionId, @RequestParam long userId) {
        User user = userService.getUserById(userId);
        Division division = divisionService.getDivisionById(divisionId);
        Set<Division> divisionSet = new HashSet<>();
        divisionSet.add(division);
        return Result.success(userService.setDivisionSet(divisionSet, user), "succeed！");
    }
}
