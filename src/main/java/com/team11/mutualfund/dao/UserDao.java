package com.team11.mutualfund.dao;

import com.team11.mutualfund.model.User;

import java.util.List;

import com.team11.mutualfund.utils.SessionUser;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public class UserDao extends AbstractDao<Long, User> {

    public void saveCustomer(User user) {
        persist(user);
    }

    public User findById(Long customerId) {
        return getByKey(customerId);
    }

    @SuppressWarnings("deprecation")
	public User findByIdForUpdate(Long customerId) {
        @SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(
                "select c from Customer c where c.id = :cid"
        )
                .setParameter("cid", customerId)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE);
        return (User) query.uniqueResult();
    }

    @SuppressWarnings("deprecation")
	public SessionUser findUserByUserName(String userName) {
        @SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(
                "select c from Customer c where c.userName = :name"
        )
                .setParameter("name", userName);
        return (SessionUser) query.uniqueResult();
    }
    
    @SuppressWarnings("deprecation")
	public User findByUserName(String userName) {
        @SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(
                "select c from Customer c where c.userName = :name"
        )
                .setParameter("name", userName);
        return (User) query.uniqueResult();
    }

    @SuppressWarnings("deprecation")
	public User findByUserNameForUpdate(String userName) {
        @SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(
                "select c from Customer c where c.userName = :name"
        )
                .setParameter("name", userName)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE);
        return (User) query.uniqueResult();
    }
    
    public User updatePassword(User c, String confirmPassword){
		return c;
    	
    }

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<User> getCustomerList() {
		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(
                "select c from Customer c"
        );
        return (List<User>) query.list();
	}


}
