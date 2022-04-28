package com.sendriods.demo.Service;

import com.sendriods.demo.Domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User getUserByName(String name);

    User addUser(String name, Integer age, String passwd);

    User updateUser(long id, String name, Integer age, String passwd);

    List<User> getAllUser();

    User deleteUserByName(String name);

    User deleteUserById(long id);

    User getUserById(long id);

    Page<User> paging(int pageNum, int pageSize);

    Page<User> findByAgeLessThanPage(int age, int pageNum, int pageSize);

    User setUserToDivision(List division, User user);
}
