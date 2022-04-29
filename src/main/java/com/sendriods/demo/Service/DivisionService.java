package com.sendriods.demo.Service;

import com.sendriods.demo.Domain.Division;
import com.sendriods.demo.Domain.User;

import java.util.List;

public interface DivisionService {
    Division getDivisionByName(String divisionName);

    Division getDivisionById(long id);

    Division addDivision(String divisionName, Integer classId);

    Division updateDivision(long id, String divisionName, Integer classId);

    List<Division> getAllDivision();

    Division addUserToDivision(Division division, User user);

    Division deleteById(long id);

    Division deleteUser(Division division);

    Division removeUserFromDivision(long userId, long divisionId);
}
