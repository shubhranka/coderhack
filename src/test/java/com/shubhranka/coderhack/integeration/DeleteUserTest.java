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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeleteUserTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void deleteUserWithStatus200()
            throws Exception {

        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(new User("test_2", "test_user", (byte) 50, null));

        mvc.perform(get("/users/test_2"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserWithStatus404()
            throws Exception {

        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(null);

        mvc.perform(get("/users/test_2"))
                .andExpect(status().isNotFound());
    }
}
