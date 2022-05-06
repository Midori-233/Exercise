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
public class UserServiceImpl implements UserService {

    /*
        @Resource
    */
    final private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByName(String name) {
        User user = userRepository.findByName(name).get();
        return user;
    }

    @Override
    public User getUserById(long id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(long id, String name, Integer age, String passwd) {
        User user = userRepository.findById(id).get();
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
        User user = userRepository.findByName(name).get();
        userRepository.delete(user);
        return user;
    }

    @Override
    public User deleteUserById(long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return user;
    }

    @Override
    public User deleteUser(User user) {
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
