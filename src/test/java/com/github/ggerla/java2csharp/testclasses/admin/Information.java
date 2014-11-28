/*
 * Copyright 2014 Giuseppe Gerla. All Rights Reserved.
 *
 * Licensed under the GNU General Public License, Version 2.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/gpl2.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.ggerla.java2csharp.testclasses.admin;

import java.util.List;

public class Information {
	private int id;

	private int defaultRole;

	private String name;

	private String surname;

	private String idNumber;

	private String telephone;

	private String mobile;

	private String fax;

	private String email;

	private String department;

	private String address;

	private boolean enabled;

	private String username;

	private String password;

	private List<Integer> roles;

	public Information(int id, int defaultRole,
			String name, String surname, String idNumber, String telephone,
			String mobile, String fax, String email, String department,
			String address, boolean enabled, String username, String password,
			List<Integer> roles) {
		super();
		this.id = id;
		this.defaultRole = defaultRole;
		this.name = name;
		this.surname = surname;
		this.idNumber = idNumber;
		this.telephone = telephone;
		this.mobile = mobile;
		this.fax = fax;
		this.email = email;
		this.department = department;
		this.address = address;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	protected Information() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(int defaultRole) {
		this.defaultRole = defaultRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
}