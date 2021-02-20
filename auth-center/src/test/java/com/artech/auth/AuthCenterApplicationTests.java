package com.artech.auth;

import com.artech.auth.service.UserService;
import com.artech.domain.entity.Role;
import com.artech.domain.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthCenterApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void addRole() {
		Role role = new Role();
		role.setName("ROLE_USER");
		role.setDescription("普通用户");
		roleRepository.save(role);
	}

	@Test
	public void addUser() {
//		User user = new User();
//		user.setLoginName("arte");
//		user.setPassword("123456");
//		user.setMobile("18612345679");
//		User addUser = userService.addUser(user);
//		System.out.println(addUser.toString());
	}

}
