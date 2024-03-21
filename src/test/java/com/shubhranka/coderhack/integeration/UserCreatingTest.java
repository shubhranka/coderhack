package com.shubhranka.coderhack.integeration;

import com.shubhranka.coderhack.entity.User;
import com.shubhranka.coderhack.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserCreatingTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void createUserWithStatus201()
            throws Exception {

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User("test_2", "test_user", (byte) 50, null));
        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(null);

        mvc.perform(post("/users/register")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":50}"))
                .andExpect(status().isCreated());

    }

    @Test
    public void createUserWithStatus409()
            throws Exception {

        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(new User("test_2", "test_user", (byte) 50, null));

        mvc.perform(post("/users/register")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":50}"))
                .andExpect(status().isConflict());

    }

    @Test
    public void createUserWithStatus400()
            throws Exception {

        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(null);

        mvc.perform(post("/users/register")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":-50}"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void createUserWithStatus400_2()
            throws Exception {

        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(null);

        mvc.perform(post("/users/register")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":150}"))
                .andExpect(status().isBadRequest());

    }
}