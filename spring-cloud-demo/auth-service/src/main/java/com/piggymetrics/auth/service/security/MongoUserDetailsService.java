package com.piggymetrics.auth.service.security;

import com.piggymetrics.auth.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MongoUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = null;
		if(username.equals("zxj")){
			user = new User();
			user.setUsername(username);
			user.setPassword(new BCryptPasswordEncoder().encode("zxj"));
		}

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return user;
	}
}
