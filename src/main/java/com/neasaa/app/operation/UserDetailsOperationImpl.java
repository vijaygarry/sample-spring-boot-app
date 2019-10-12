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

package com.neasaa.app.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.neasaa.app.dao.UserDao;
import com.neasaa.app.entity.User;

@Component ("userDetailsOperation")
@Scope ("prototype")
public class UserDetailsOperationImpl extends AbstractOperation<User> implements UserDetailsOperation {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User doExecute (String aInput) {
		System.out.println("Instance id: " + this);
		return userDao.getUserDetails(aInput);
	}

}
