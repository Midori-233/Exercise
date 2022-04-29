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
import java.util.List;
import java.util.Objects;

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

	@ManyToMany
	@JsonIgnore
	//@JsonIgnoreProperties
	//@JoinColumn(name = "division_id")
	private List<Division> divisionList;

	public User() {
	}

	public User(long id, String name, Integer age, String passwd, List<Division> divisionList) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.passwd = passwd;
		this.divisionList = divisionList;
	}

	public void addDivision(Division division) {
		divisionList.add(division);
	}

	public void removeDivision(Division division) {
		divisionList.remove(division);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id && Objects.equals(name, user.name) && Objects.equals(age, user.age) && Objects.equals(passwd, user.passwd) && Objects.equals(divisionList, user.divisionList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, age, passwd, divisionList);
	}
}
