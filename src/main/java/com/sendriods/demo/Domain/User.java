/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sendriods.demo.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for objects
 * needing this property.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
// lombok
@Entity
@Data
@Table(name = "user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	@Column(name = "passwd")
	private String passwd;

/*	  关于 cascade 的考量，默认情况下是不加的。
	  需要根据业务需求来进行添加，绝不能一上来就给 ALL。*/
	@ManyToMany
	@JsonIgnore
/*	  对于 多对多关系 和 一对多 关系中使用 Set 还是 List 是要有考量的
	  1. 如果对顺序有要求，一般用 List，否则用 Set
	  2. sql 层面，更新 list 的时候，会先将原来的元素全部remove 然后再 一条条 insert
	              更新 set 的时候，只会修改有变动的那一条数据
	  3. 一般这种集合最好给个默认值，不然很容易出现空指针异常（这是我个人的理解，公司没有硬性规定，也没有一个定论）*/
	private Set<Division> divisionSet = new HashSet<>();

	public void addDivision(Division division) {
		divisionSet.add(division);
	}

	public void removeDivision(Division division) {
		divisionSet.remove(division);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id && Objects.equals(name, user.name) && Objects.equals(age, user.age) && Objects.equals(passwd, user.passwd) && Objects.equals(divisionSet, user.divisionSet);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, age, passwd, divisionSet);
	}
}
