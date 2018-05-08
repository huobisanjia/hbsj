package com.hbsj.user.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hbsj.entity.User;
import com.hbsj.user.dao.addUser;


@Service
@Transactional
public class UserService {
	@Resource
	private addUser adduser;
	public Integer add(User u){
		return adduser.add(u);
	}
	
}
