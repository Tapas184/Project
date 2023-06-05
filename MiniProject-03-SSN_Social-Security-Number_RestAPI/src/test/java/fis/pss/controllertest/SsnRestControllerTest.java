package fis.pss.controllertest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import fis.ssn.restcontoller.SsnRestController;

@WebMvcTest(value = SsnRestController.class)
public class SsnRestControllerTest {

	@Autowired
	private MockMvc mokMvc;
	
	
	@Test
	public void customerRegistrationTest() throws Exception {
		
		MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/generateSsn");
		MvcResult result = mokMvc.perform(post).andReturn();
		MockHttpServletResponse response = result.getResponse();
		int status = response.getStatus();
		
		assertEquals(200, status);
	}
}
