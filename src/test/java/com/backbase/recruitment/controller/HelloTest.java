package com.backbase.recruitment.controller;

import com.backbase.recruitment.configuration.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class HelloTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnHelloBackbase() throws Exception {
        //given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello");
        ResultMatcher body = MockMvcResultMatchers.content().string("HELLO BACKBASE");
        ResultMatcher okStatus = MockMvcResultMatchers.status().isOk();

        //when
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(body);
        resultActions.andExpect(okStatus);
    }
}
