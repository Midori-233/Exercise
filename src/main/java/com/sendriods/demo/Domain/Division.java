package com.sendriods.demo.Domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "division")
public class Division implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "divisionName")
    private String divisionName;
    @Column(name = "divisionId")
    private Integer divisionId;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "divisionList")
/*    @JoinTable(name = "Division_User",
            joinColumns = {
                @JoinColumn(name = "division_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "user_id", referencedColumnName ="id")})*/
    private List<User> userList;

    public Division() {
    }

    public Division(long id, String divisionName, Integer divisionId, List<User> userList) {
        this.id = id;
        this.divisionName = divisionName;
        this.divisionId = divisionId;
        this.userList = userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void removeUser(User user) {
        userList.remove(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return Objects.equals(id, division.id) && Objects.equals(divisionName, division.divisionName) && Objects.equals(divisionId, division.divisionId) && Objects.equals(userList, division.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, divisionName, divisionId, userList);
    }
}
