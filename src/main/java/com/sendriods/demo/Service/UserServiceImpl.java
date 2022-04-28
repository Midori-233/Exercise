package com.sendriods.demo.Service;

import com.sendriods.demo.Dao.UserRepository;
import com.sendriods.demo.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User getUserByName(String name) {
        User user = userRepository.findByName(name);
        return user;
    }

    @Override
    public User getUserById(long id) {
        User user = userRepository.findById(id);
        return user;
    }

    @Override
    public User addUser(String name, Integer age, String passwd) {
        User user = new User();
        user.setName(name);
        user.setAge((Integer) age);
        user.setPasswd(passwd);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(long id, String name, Integer age, String passwd) {
        User user = userRepository.findById(id);
        user.setName(name);
        user.setAge(age);
        user.setPasswd(passwd);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User deleteUserByName(String name) {
        User user = userRepository.findByName(name);
        userRepository.delete(user);
        return user;
    }

    @Override
    public User deleteUserById(long id) {
        User user = userRepository.findById(id);
        userRepository.delete(user);
        return user;
    }

    @Override
    public Page<User> paging(int pageNum, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<User> userPage = userRepository.findAll(pageable);
        userPage.getContent().forEach(System.out::println);
        return userPage;
    }

    @Override
    public Page<User> findByAgeLessThanPage(int age, int pageNum, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<User> userPage = userRepository.findByAgeLessThan(age, pageable);
        return userPage;
    }

    @Override
    public User setUserToDivision(List division, User user) {
        user.setDivision(division);
        userRepository.save(user);
        return user;
    }
}
