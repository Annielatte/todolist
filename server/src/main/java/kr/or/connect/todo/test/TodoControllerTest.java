package kr.or.connect.todo.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.springframework.http.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;

import kr.or.connect.todo.TodoApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TodoApplication.class)
@WebAppConfiguration
public class TodoControllerTest{

	@Autowired
	
	WebApplicationContext wac;
	MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = webAppContextSetup(this.wac)
			.alwaysDo(print(System.out))
			.build();
	}
	@Test
	public void shouldCreate() throws Exception {
		String requestBody = "{\"completed\":\"0\", \"todo\":\"할일\"}";

		mvc.perform(
			post("/api/todos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
			)
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.todo").value("할일"))
			.andExpect(jsonPath("$.date").exists())
			.andExpect(jsonPath("$.completed").value("0"));
	}
	
	
	@Test
	public void shouldUpdate() throws Exception{
		String requestBody = "{\"completed\":\"1\"}";
		
		mvc.perform(
				put("/api/todos/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody)
				)
			
				.andExpect(jsonPath("$.completed").value("1"));
		}
		
	@Test
	public void shouldDelete() throws Exception{
		mvc.perform(
				delete("/api/todos/")
					.contentType(MediaType.APPLICATION_JSON)
			
				)
	    .andExpect(status().isOk());
	}

}
