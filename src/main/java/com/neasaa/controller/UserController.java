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

package com.neasaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neasaa.app.SpringUtil;
import com.neasaa.app.entity.User;
import com.neasaa.app.operation.UserDetailsOperation;

@RestController
public class UserController {
	
	@Qualifier ("springUtil")
	@Autowired (required = true)
	private SpringUtil springUtil;
	
	
    @RequestMapping("/")
    public String index() {
        return "Welcome to sample Neasaa Spring Boot Application!!!";
    }

    @RequestMapping(path= "/user/{aName}", method= RequestMethod.GET)
    public User getUserDetails (@PathVariable String aName) {
    	UserDetailsOperation operation = springUtil.getBeanInstance("userDetailsOperation", UserDetailsOperation.class);
    	return operation.execute(aName);
    }
    
}