package com.sendriods.demo.Domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "division")
public class Division implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*
      column name 一般是用 _ 分隔单词 而不是驼峰命名法
      也可以不填直接用 hibernate 生成，一般遇到数据库关键字时才会指定，比如 row column 之类的
    */
    @Column(name = "division_name")
    private String divisionName;
    @Column(name = "division_id")
    private Integer divisionId;

    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "divisionSet")
    @ToString.Exclude
    private Set<User> userSet = new HashSet<>();

    @OneToOne
    private Director director;

    public void addUser(User user) {
        userSet.add(user);
    }

    public void removeUser(User user) {
        userSet.remove(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return Objects.equals(id, division.id) && Objects.equals(divisionName, division.divisionName) && Objects.equals(divisionId, division.divisionId) && Objects.equals(userSet, division.userSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, divisionName, divisionId, userSet);
    }
}
