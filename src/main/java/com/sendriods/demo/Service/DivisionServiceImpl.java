package com.sendriods.demo.Service;

import com.sendriods.demo.Dao.DivisionRepository;
import com.sendriods.demo.Dao.UserRepository;
import com.sendriods.demo.Domain.Division;
import com.sendriods.demo.Domain.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// TODO 公司为了方便编码，现在是直接使用类的，没有声明 Service 的接口。
//  如果真有业务需要，才会定义一个接口多个实现类。
@Service
public class DivisionServiceImpl implements DivisionService {
/*    @Resource
    private DivisionRepository divisionRepository;
    @Resource
    private UserRepository userRepository;*/

    /*
        TODO 目前我们都使用构造函数这种注入方式，不使用注解注入（controller 注入同理）
    */
    final private DivisionRepository divisionRepository;
    final private UserRepository userRepository;

    public DivisionServiceImpl(DivisionRepository divisionRepository, UserRepository userRepository) {
        this.divisionRepository = divisionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Division getDivisionByName(String divisionName) {
        Optional<Division> division = divisionRepository.findByDivisionName(divisionName);
        return division.get();
    }

    @Override
    public Division getDivisionById(long id) {
        Optional<Division> division = divisionRepository.findById(id);
        return division.get();
    }

    @Override
    public Division addDivision(String divisionName, Integer classId) {
        Division division = new Division();
        division.setDivisionName(divisionName);
        division.setDivisionId(classId);
        System.out.println(division.toString());
        divisionRepository.save(division);
        return division;
    }

    @Override
    public Division addUserToDivision(Division division, User user) {
        Set<Division> divisionSet = new HashSet<>();
        divisionSet.add(division);
        user.setDivisionSet(divisionSet);
        division.addUser(user);
        userRepository.save(user);
        divisionRepository.save(division);
        return division;
    }

    @Override
    public Division updateDivision(Division division) {
        divisionRepository.save(division);
        return division;
    }

    @Override
    public List<Division> getAllDivision() {
        return divisionRepository.findAll();
    }

    public Division deleteById(long id) {
        // TODO 可以 deleteById
        Division division = divisionRepository.findById(id).get();
        divisionRepository.delete(division);
        return division;
    }

    @Override
    public Division deleteUser(Division division) {
        divisionRepository.delete(division);
        return division;
    }

    @Override
    public Division removeUserFromDivision(long userId, long divisionId) {
        Division division = divisionRepository.findById(divisionId).get();
        User user = userRepository.findById(userId).get();
        division.removeUser(user);
        divisionRepository.save(division);
        return division;
    }
}
