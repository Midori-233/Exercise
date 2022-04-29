package com.sendriods.demo.Service;

import com.sendriods.demo.Dao.DivisionRepository;
import com.sendriods.demo.Dao.UserRepository;
import com.sendriods.demo.Domain.Division;
import com.sendriods.demo.Domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DivisionServiceImpl implements DivisionService {
    @Resource
    private DivisionRepository divisionRepository;
    @Resource
    private UserRepository userRepository;

    @Override
    public Division getDivisionByName(String divisionName) {
        Division division = divisionRepository.findByDivisionName(divisionName);
        return division;
    }

    @Override
    public Division getDivisionById(long id) {
        Division division = divisionRepository.findById(id);
        return division;
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
        List<Division> divisionList = new ArrayList<>();
        divisionList.add(division);
        user.setDivisionList(divisionList);
        division.addUser(user);
        userRepository.save(user);
        divisionRepository.save(division);
        return division;
    }

    @Override
    public Division updateDivision(long id, String divisionName, Integer classId) {
        Division division = divisionRepository.findById(id);
        division.setDivisionName(divisionName);
        division.setDivisionId(classId);
        divisionRepository.save(division);
        return division;
    }

    @Override
    public List<Division> getAllDivision() {
        return divisionRepository.findAll();
    }

    public Division deleteDivisionById(long id) {
        Division division = divisionRepository.findById(id);
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
        Division division = divisionRepository.findById(divisionId);
        User user = userRepository.findById(userId);
        division.removeUser(user);
        divisionRepository.save(division);
        return division;
    }
}
