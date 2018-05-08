package com.hbsj.user.dao;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.hbsj.entity.User;
@Repository
public class addUser {
	@Resource
	private SessionFactory sessionFactory;
	public Integer add(User u){
		try {
			this.sessionFactory.getCurrentSession().save(u);
			return u.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
