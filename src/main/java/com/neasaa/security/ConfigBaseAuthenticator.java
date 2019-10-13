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

package com.neasaa.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ConfigBaseAuthenticator extends AbstractUserDetailsAuthenticationProvider {

	@Override
	protected void additionalAuthenticationChecks(UserDetails aUserDetails,
			UsernamePasswordAuthenticationToken aAuthentication) throws AuthenticationException {
		if (aAuthentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}

		String presentedPassword = aAuthentication.getCredentials().toString();

		//if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
		if (!presentedPassword.equals(aUserDetails.getPassword())) {
			logger.debug("Authentication failed: password does not match stored value");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
		
	}

	@Override
	protected UserDetails retrieveUser(String aUsername, UsernamePasswordAuthenticationToken aAuthentication)
			throws AuthenticationException {
		try {
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			//Read the user info from config/database and authorize
			if(aUsername.equalsIgnoreCase("vijay")) {
				return new User("vijay", "vijay1", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,AuthorityUtils.NO_AUTHORITIES);
			} else if(aUsername.equalsIgnoreCase("avy")) {
				return new User("avy", "avy1", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,AuthorityUtils.NO_AUTHORITIES);
			}
			throw new UsernameNotFoundException ("User not found");
		}
		catch (UsernameNotFoundException ex) {
			throw ex;
		}
		catch (InternalAuthenticationServiceException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
		}
	}
}
