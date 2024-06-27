package com.sparta.plusproject.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameGetUser(username);

		// if (user.getStatus() == UserStatusEnum.NON_USER) {
		// 	throw new UsernameNotFoundException(user.getUsername());
		// }

		return new UserDetailsImpl(user);
	}
}