/*
 * Copyright 2019 neasaa.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.neasaa.app.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	
	private static final long serialVersionUID = -5928968239747205500L;
	
	private String userId;
	private String name;
	private String lastName;
	private int age;
	private List<String> address;
	
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String aUserId) {
		this.userId = aUserId;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String aName) {
		this.name = aName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String aLastName) {
		this.lastName = aLastName;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(int aAge) {
		this.age = aAge;
	}
	public List<String> getAddress() {
		return this.address;
	}
	public void setAddress(List<String> aAddress) {
		this.address = aAddress;
	}
	
	
}
