package com.sendriods.demo.Service;

import com.sendriods.demo.Domain.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface UserService {

    User getUserByName(String name);

    User addUser(String name, Integer age, String passwd);

    User updateUser(long id, String name, Integer age, String passwd);

    List<User> getAllUser();

    User deleteUserByName(String name);

    User deleteUserById(long id);

    User deleteUser(User user);

    User getUserById(long id);

    Page<User> paging(int pageNum, int pageSize);

    Page<User> findByAgeLessThanPage(int age, int pageNum, int pageSize);

    User setDivisionSet(Set divisionSet, User user);

    User addDivisionSet(Set divisionSet, User user);
}
