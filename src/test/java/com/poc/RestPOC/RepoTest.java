package com.poc.RestPOC;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.poc.RestPOC.dao.UserRepository;
import com.poc.RestPOC.model.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RepoTest {

	@Autowired
	private UserRepository userRepo; 
	
	@Test
	@Order(1)
	public void testCreateUser() {
		User user = new User();
		user.setId(1);
		user.setName("Ram");
		user.setEmail("ram@gmail.com");
		user.setSalary(12000);
		userRepo.save(user);
		assertNotNull(userRepo.findById(1).get());
	}
	
	@Test
	@Order(2)
	public void testReadAllUser() {
		List<User> list = userRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void testReadSingleUser() {
		User user = userRepo.findById(1).get();
		assertEquals("Ram", user.getName());
	}
	
	@Test
	@Order(4)
	public void testUpdateUser() {
		User user = userRepo.findById(1).get();
		user.setSalary(18000);
		userRepo.save(user);
		assertNotEquals(12000, userRepo.findById(1).get().getSalary());
	}
	
	@Test
	@Order(5)
	public void testDeleteUser() {
		userRepo.deleteById(1);
		assertThat(userRepo.existsById(1)).isFalse();
	}
}
