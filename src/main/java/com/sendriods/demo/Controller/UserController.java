package com.sendriods.demo.Controller;

import com.sendriods.demo.Domain.Division;
import com.sendriods.demo.Domain.User;
import com.sendriods.demo.Service.DivisionService;
import com.sendriods.demo.Service.UserService;
import com.sendriods.demo.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    final private UserService userService;

    final private DivisionService divisionService;

    public UserController(UserService userService, DivisionService divisionService) {
        this.userService = userService;
        this.divisionService = divisionService;
    }

    @PostMapping("/addUser")
    public Result<User> addUser(@RequestBody User user) {
        //  jpa save/update 之后是不会返回 null 的，如果发生错误的话会抛出异常而不会返回 null
        return Result.success(userService.addUser(user), "succeed！");
    }

    //  update 使用 @PutMapping
    @PutMapping("/updateUser")
    public Result<User> updateUser(@RequestBody User user) {
        return Result.success(user, "succeed！");
    }

    /*
      对于可能为空的类型，建议使用 Optional
      参考 https://github.com/100TB/OnJava8/blob/master/docs/book/14-Streams.md#optional%E7%B1%BB
      顺便可以看看 Stream 的 API
    */
    @GetMapping("/getUserById")
    public Result getUserById(@RequestParam Optional<Long> id) {
        return id.map(
                i -> Result.success(userService.getUserById(id.get()), "succeed！")
        ).orElse(Result.error("234", "No Value Input"));
    }

    @GetMapping("/getUserByName")
    public Result getUserByName(@RequestParam Optional<String> name) {
        return name.map(
                i -> Result.success(userService.getUserByName(name.get()), "succeed！")
        ).orElse(Result.error("234", "No Value Input"));
    }

    @GetMapping("/getAllUser")
    public Result getAllUser() {
        List<User> userList = userService.getAllUser();
        return Result.success(userList, "succeed!");
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @DeleteMapping("/deleteByName")
    public Result deleteUserByName(@RequestBody String name) {
        userService.deleteUserByName(name);
        return Result.success("succeed！");
    }

    @DeleteMapping("/deleteById")
    public Result deleteUserById(@RequestBody long id) {
        userService.deleteUserById(id);
        return Result.success("succeed！!");
    }

    @DeleteMapping("/deleteUser")
    public Result deleteUser(User user) {
        userService.deleteUser(user);
        return Result.success("succeed！!");
    }

    @GetMapping("/getAllPage")
    public Result getAllPage(@RequestBody int pageNum, @RequestBody int pageSize) {
        Page<User> userPage = userService.paging(pageNum, pageSize);
        return Result.success(userPage, "succeed！");
    }

    @GetMapping("/getByAgeLessThanPage")
    public Result getByAgeLessThanPage(@RequestBody int age, @RequestBody int pageNum, @RequestBody int pageSize) {
        Page<User> userPage = userService.findByAgeLessThanPage(age, pageNum, pageSize);
        return Result.success(userPage, "succeed！");
    }

    @PostMapping("/setDivisionToUser")
    public Result setDivisionToUser(@RequestBody long divisionId, @RequestBody long userId) {
        Set<Division> divisionSet = new HashSet<>();
        divisionSet.add(divisionService.getDivisionById(divisionId));
        return Result.success(userService.setDivisionSet(divisionSet, userService.getUserById(userId)), "succeed！");
    }
}
