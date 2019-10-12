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

package com.neasaa.app;

import org.springframework.beans.factory.annotation.Value;

public class DataSourceConfig {
	
	@Value("${db.driver.classname}")
	private String driverClass;
	
	@Value("${db.url}")
	private String dbUrl;

	@Value("${db.username}")
	private String dbUser;
	
	@Value("${db.password}")
	private String dbEncryptedPwd;
	
	//Plain text pwd
	private String dbPwd;
	
	@Value("${db.validation.query}")
	private String validationQuery;

	
	public String getDriverClass() {
		return this.driverClass;
	}

	public String getDbUrl() {
		return this.dbUrl;
	}

	public String getDbUser() {
		return this.dbUser;
	}

	public String getDbPwd() {
		if(this.dbPwd == null) {
			if(dbEncryptedPwd.equals("hdbJwL+L70TU5vF62dZTtd6PHd1ebTk+")) {
				//call decrypt utility here to decrypt the password.
				this.dbPwd = "password";
			}
		}
		return this.dbPwd;
	}

	public String getValidationQuery() {
		return this.validationQuery;
	}

	@Override
	public String toString() {
		return "DataSourceConfig [driverClass=" + this.driverClass + ", dbUrl=" + this.dbUrl + ", dbUser=" + this.dbUser
				+ ", validationQuery=" + this.validationQuery + "]";
	}	
}
