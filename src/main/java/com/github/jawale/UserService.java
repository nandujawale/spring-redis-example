package com.github.jawale;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User getUser(String id) {
		return userRepository.findById(id).orElseThrow();
	}

	public void save(User user) {
		userRepository.save(user);
	}
}
