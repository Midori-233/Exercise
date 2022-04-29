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
    // TODO column name 一般是用 _ 分隔单词 而不是驼峰命名法
    //  也可以不填直接用 hibernate 生成，一般遇到数据库关键字时才会指定，比如 row column 之类的
    @Column(name = "divisionName")
    private String divisionName;
    @Column(name = "divisionId")
    private Integer divisionId;

    // FIXME toString 循环调用了
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

    // FIXME equals 循环调用了
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
