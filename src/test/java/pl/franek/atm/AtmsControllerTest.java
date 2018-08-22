package pl.franek.atm;

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
import pl.franek.configuration.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class AtmsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnBadRequest() throws Exception {
        //given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/atms");
        ResultMatcher badRequestStatus = MockMvcResultMatchers.status().isBadRequest();

        //when
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(badRequestStatus);
    }

    @Test
    public void shouldReturnBadRequestAndMessageInBody() throws Exception {
        //given
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/atms?city=");
        ResultMatcher body = MockMvcResultMatchers.content().string("Required query parameter 'city' can't be empty");
        ResultMatcher badRequestStatus = MockMvcResultMatchers.status().isBadRequest();

        //when
        ResultActions resultActions = mockMvc.perform(requestBuilder);

        //then
        resultActions.andExpect(badRequestStatus);
        resultActions.andExpect(body);
    }
}
