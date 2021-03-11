package com.heasy.knowroute.test.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseTest {
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;
    
    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders
        		.webAppContextSetup(wac)
        		.build();
    }
    
    public void get(String url) throws Exception {
    	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
    			.get(url)
        		.contentType(MediaType.APPLICATION_JSON);
    	perform(builder);
    }
    
    public void post(String url, String jsonContent) throws Exception {
    	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
    			.post(url)
        		.contentType(MediaType.APPLICATION_JSON)
        		.characterEncoding("utf-8")
        		.content(jsonContent);
    	perform(builder);
    }

    public void perform(MockHttpServletRequestBuilder builder) throws Exception {
		MvcResult mvcResult = mvc.perform(builder)
    		.andExpect(MockMvcResultMatchers.status().isOk())
//    		.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1))
    		.andDo(MockMvcResultHandlers.print())
    		.andReturn();
    	System.out.println(mvcResult.getResponse().getContentAsString());
	}
    
}
