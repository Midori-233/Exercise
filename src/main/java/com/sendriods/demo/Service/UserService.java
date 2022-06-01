package com.sendriods.demo.Service;

import com.sendriods.demo.Dao.UserRepository;
import com.sendriods.demo.Domain.Division;
import com.sendriods.demo.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByName(String name) {
        User user = userRepository.findByUsername(name).get();
        return user;
    }

    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User updateUser(long id, String name, Integer age, String passwd) {
        User user = userRepository.findById(id).get();
        user.setUsername(name);
        user.setAge(age);
        user.setPasswd(passwd);
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User deleteUserByName(String name) {
        User user = userRepository.findByUsername(name).get();
        userRepository.delete(user);
        return user;
    }

    public User deleteUserById(long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return user;
    }

    public User deleteUser(User user) {
        userRepository.delete(user);
        return user;
    }

    public Page<User> paging(int pageNum, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<User> userPage = userRepository.findAll(pageable);
        userPage.getContent().forEach(System.out::println);
        return userPage;
    }

    public Page<User> findByAgeLessThanPage(int age, int pageNum, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<User> userPage = userRepository.findByAgeLessThan(age, pageable);
        return userPage;
    }

    public User setDivisionSet(Set divisionSet, User user) {
        user.setDivisionSet(divisionSet);
        userRepository.save(user);
        return user;
    }

    public User addDivisionSet(Set divisionSet, User user) {
        for (Object division : divisionSet) {
            user.addDivision((Division) division);
        }
        userRepository.save(user);
        return user;
    }
}
