package com.sendriods.demo.Controller;

import com.sendriods.demo.Domain.Division;
import com.sendriods.demo.Domain.User;
import com.sendriods.demo.Service.DivisionService;
import com.sendriods.demo.Service.UserService;
import com.sendriods.demo.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private DivisionService divisionService;

    @PostMapping("/addUser")
    public Result<User> addUserController(@RequestParam String name, @RequestParam Integer age, @RequestParam String passwd) {
        // TODO jpa save/update 之后是不会返回 null 的，如果发生错误的话会抛出异常而不会返回 null
        User user = userService.addUser(name, age, passwd);
        if (user != null) {
            return Result.success(user, "注册成功！");
        } else {
            return Result.error("123", "注册失败！");
        }
    }

    // TODO update 使用 @PutMapping
    @PostMapping("/updateUser")
    public Result<User> updateUserController(@RequestParam long id, @RequestParam String name, @RequestParam Integer age, @RequestParam String passwd) {
        User user = userService.updateUser(id, name, age, passwd);
        if (user != null) {
            return Result.success(user, "更新成功！");
        } else {
            return Result.error("123", "更新失败！");
        }
    }

    @PostMapping("/getUserById")
    public Result<User> getUserById(@RequestParam long id) {
        // TODO 对于可能为空的类型，建议使用 Optional
        //  参考 https://github.com/100TB/OnJava8/blob/master/docs/book/14-Streams.md#optional%E7%B1%BB
        //  顺便可以看看 Stream 的 API
        User user = userService.getUserById(id);
        if (user != null) {
            return Result.success(user, "OK");
        } else {
            return Result.error("234", "N");
        }
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
    public List<User> getAllUser() {
        List<User> userList = userService.getAllUser();
        return userList;
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
        List<Division> divisionList = new ArrayList<>();
        divisionList.add(division);
        return Result.success(userService.setUserToDivision(divisionList, user), "succeed！");
    }
}
