package com.shubhranka.coderhack.integeration;

import com.shubhranka.coderhack.entity.User;
import com.shubhranka.coderhack.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UpdateUserTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    User testUser = new User("test_2", "test_user", (byte) 50, null);

    @Test
    public void updateUserWithStatus200()
            throws Exception {

        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(testUser);

        mvc.perform(put("/users/test_2")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":50}"))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldHave1BadgeWhenScoreIs29() throws Exception {
        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(testUser);
        mvc.perform(put("/users/test_2")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":29}"))
                .andExpect(status().isOk());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userCaptor.capture());

        assertEquals(1, userCaptor.getValue().getBadges().size());

    }

    @Test
    public void shouldHave2BadgesWhenScoreIs50() throws Exception {
        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(testUser);
        mvc.perform(put("/users/test_2")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":50}"))
                .andExpect(status().isOk());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userCaptor.capture());

        assertEquals(2, userCaptor.getValue().getBadges().size());
    }

    @Test
    public void shouldHave3BadgesWhenScoreIs100() throws Exception {
        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(testUser);
        mvc.perform(put("/users/test_2")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":100}"))
                .andExpect(status().isOk());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userCaptor.capture());

        assertEquals(3, userCaptor.getValue().getBadges().size());
    }

    @Test
    public void updateUserWithStatus404()
            throws Exception {

        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(null);

        mvc.perform(put("/users/test_2")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":50}"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void updateUserWithStatus400()
            throws Exception {

        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(testUser);

        mvc.perform(put("/users/test_2")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":-50}"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateUserWithStatus400_2()
            throws Exception {

        Mockito.when(userRepository.findByUserId(Mockito.anyString())).thenReturn(testUser);

        mvc.perform(put("/users/test_2")
                        .contentType("application/json")
                        .content("{\"userId\":\"test_2\",\"score\":150}"))
                .andExpect(status().isBadRequest());

    }
}
