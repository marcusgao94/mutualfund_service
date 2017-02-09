package com.team11.mutualfund.service;


import com.team11.mutualfund.dao.UserDao;
import com.team11.mutualfund.model.User;

import static com.team11.mutualfund.utils.Constant.WRONGPASSWORD;

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

	public User getCustomerById(long id) {
		return userDao.findById(id);
	}

	public User getCustomerByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	public List<User> getCustomerList() {
		return userDao.getCustomerList();
	}

    @Transactional(isolation = Isolation.SERIALIZABLE)
	public User updatePassword(Long cid, String originPassword, String newPassword)
			throws RollbackException {
		User c =  userDao.findByIdForUpdate(cid);
		if (c == null)
			throw new RollbackException(NOCUSTOMER);
		if (!c.getPassword().equals(originPassword))
			throw new RollbackException(WRONGPASSWORD);
		c.setPassword(newPassword);
		return c;
	}

	public User updatePassword(String userName, String originPassword, String newPassword) throws RollbackException {
		User c =  userDao.findByUserNameForUpdate(userName);
		if (c == null)
			throw new RollbackException(NOCUSTOMER);
		if (!c.getPassword().equals(originPassword))
			throw new RollbackException("Password has been reset by other employees");
		c.setPassword(newPassword);
		return c;
	}

}
