/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.sample.data;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.mock.http.client.MockClientHttpRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import sample.WebMvcConfiguration;
import sample.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={WebMvcConfiguration.class})
public class SpringDataRestTests {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private User user;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(context)
            .defaultRequest(
                post("/")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .alwaysDo(print())
                .build();

        user = new User();
        user.setFirstName("First");
        user.setLastName("Last");
        user.setEmail("flast@example.com");
    }

    @Test
    public void users() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    public void user() throws Exception {
        mockMvc.perform(get("/users/1")).andExpect(status().isOk());
    }

    @Test
    public void firstLastNameEqual() throws Exception {
        user.setLastName("First");

        MockHttpServletRequestBuilder request = post("/users/").content(getUserAsJson());

        mockMvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].message").value("User firstName and lastName cannot be equal."));

    }

    @Test
    public void invalidEmail() throws Exception {
        user.setEmail("invalid");

        MockHttpServletRequestBuilder request = post("/users/").content(getUserAsJson());

        mockMvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors[0].message").value("Invalid Email"));

    }

    @Test
    public void discover() throws Exception {

        MockHttpServletRequestBuilder request = get("/");

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.links[?(@.rel == 'users')].href").value("http://localhost/users"))
            .andExpect(jsonPath("$.links[?(@.rel == 'messages')].href").value("http://localhost/messages"));

    }

    @Test
    public void schema() throws Exception {

        MockHttpServletRequestBuilder request = get("/users/schema").accept(new MediaType("application","schema+json"));

        mockMvc.perform(request)
            .andExpect(status().isOk());

    }


    @Test
    public void usersSearchSchema() throws Exception {

        MockHttpServletRequestBuilder request = get("/users/search");

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.links[?(@.rel == 'findByLastName')].href").value("http://localhost/users/search/findByLastName"))
            .andExpect(jsonPath("$.links[?(@.rel == 'findByFirstNameAndLastName')].href").value("http://localhost/users/search/findByFirstNameAndLastName"));

    }

    @Test
    public void usersFindByLastName() throws Exception {

        MockHttpServletRequestBuilder request = get("/users/search/findByLastName?lastName=Winch");

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].lastName").value("Winch"));

    }

    @Test
    public void compact() throws Exception {

        MockHttpServletRequestBuilder request = get("/messages/").accept(new MediaType("application","x-spring-data-compact+json"));

        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.links[?(@.href == 'http://localhost/messages/100')].rel").value("message"))
            .andExpect(jsonPath("$.links[?(@.href == 'http://localhost/messages/search')].rel").value("search"));

    }

    private String getUserAsJson() throws IOException {

        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        MockClientHttpRequest output = new MockClientHttpRequest();

        converter.write(user, MediaType.APPLICATION_JSON, output);
        return output.getBodyAsString();
    }

}
