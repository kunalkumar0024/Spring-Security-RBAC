package com.kunal.advocateConnect.service.impl;

import com.kunal.advocateConnect.entity.User;
import com.kunal.advocateConnect.entity.UserDetailsImpl;
import com.kunal.advocateConnect.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User not exist" + username));
		return UserDetailsImpl.build(user);
	}

}
