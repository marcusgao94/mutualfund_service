package com.team11.mutualfund.service;


import com.team11.mutualfund.dao.UserDao;
import com.team11.mutualfund.model.User;


import java.util.List;

import javax.transaction.RollbackException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static com.team11.mutualfund.utils.Constant.*;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public void createCustomer(User user) throws DataIntegrityViolationException {
		userDao.saveCustomer(user);
	}

	public User getUserById(long id) {
		return userDao.findById(id);
	}

	public User getUserByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	public List<User> getCustomerList() {
		return userDao.getCustomerList();
	}

}
