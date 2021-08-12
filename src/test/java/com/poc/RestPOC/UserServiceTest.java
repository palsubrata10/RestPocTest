package com.poc.RestPOC;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.poc.RestPOC.dao.UserRepository;
import com.poc.RestPOC.model.User;
import com.poc.RestPOC.services.UserService;

@SpringBootTest
public class UserServiceTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	User user1 = new User(1, "Abc", "Abc@gmail.com", 12000);
	User user2 = new User(2, "efg", "efg@gmail.com", 23000);
	User user3 = new User(3, "xyz", "xyz@gmail.com", 50000);

	@Test
	public void testGetAllUser_Sucess() throws Exception {

		List<User> list = Arrays.asList(user1, user2, user3);
		Mockito.when(userRepository.findAll()).thenReturn(list);
		List<User> userList = userService.getAllUser();
		assertEquals(3,userList.size());
	}
	
	@Test
	public void testGetAllUser_null() throws Exception {

		Mockito.when(userRepository.findAll()).thenReturn(null);
		List<User> userList = userService.getAllUser();
		assertNull(userList);
	}
	
	@Test
	public void testGetUserById_Sucess() throws Exception {
		
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user1));
		User user = userService.getUserById(1);
		assertEquals(1, user.getId());
	}
	
	@Test
	public void testCreateUser() throws Exception {
		
		Mockito.when(userRepository.save(Mockito.anyObject())).thenReturn(user1);
		User oneUser = userService.createUser(Mockito.any());
		assertNotNull(oneUser);
	}
}
