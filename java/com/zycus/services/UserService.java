package com.zycus.services;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zycus.entity.users.User;
import com.zycus.repository.UserRepository;

@Service
@Transactional
public class UserService implements Services<User, String> {

	@Autowired
	UserRepository userRepository;
	
	@CacheEvict(value = "allUsers.cache", allEntries = true)
	public void addNew(User t) {
		userRepository.save(t);
	}

	@Cacheable(value = "allUsers.cache")
	public Iterable<User> fetchAll() {
		return userRepository.findAll();
	}

	public User fetchById(String id) {
		return userRepository.findById(id).get();
	}
	
	public boolean validateUser(User user, HttpServletRequest request) {
		User fetchedUser = userRepository.findById(user.getUsername()).get();
			
		if(fetchedUser == null) {
			return false;
		}
		else if(fetchedUser.equals(user)){
			request.getSession().setAttribute("adminName", fetchedUser.getName());
			
			return true;
		}
		else {
			return false;
		}
	}
}
