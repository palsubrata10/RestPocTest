package com.poc.RestPOC;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.poc.RestPOC.controller.UserContoller;
import com.poc.RestPOC.model.User;
import com.poc.RestPOC.services.UserService;

@WebMvcTest(UserContoller.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	User user1 = new User(1, "Abc", "Abc@gmail.com", 12000);
	User user2 = new User(2, "efg", "efg@gmail.com", 23000);
	User user3 = new User(3, "xyz", "xyz@gmail.com", 5000);
	
	@Test
	public void getAllUserRecords_success() throws Exception {
		List<User> records = Arrays.asList(user1,user2,user3);
		Mockito.when(userService.getAllUser()).thenReturn(records);
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/jpa/users")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", hasSize(3)))
	            .andExpect(jsonPath("$[2].name", is("xyz")));
	}
}
