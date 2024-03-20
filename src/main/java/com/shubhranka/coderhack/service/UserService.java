package com.shubhranka.coderhack.service;

import com.shubhranka.coderhack.entity.User;
import com.shubhranka.coderhack.enums.Badge;
import com.shubhranka.coderhack.exceptions.UserException;
import com.shubhranka.coderhack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User createUser(User user) {
        validateUser(user);
        updateBadges(user);
        userRepository.save(user);

        return user;
    }

    private void validateUser(User user) {
        if(user.getScore()<0)
            throw new UserException("Score cannot be negative", HttpStatus.BAD_REQUEST);
        if(user.getScore()>100)
            throw new UserException("Score cannot be greater than 100", HttpStatus.BAD_REQUEST);
    }

    private void updateBadges(User user) {
        Badge[] badges = Badge.values();
        user.setBadges(new ArrayList<>());
        for(Badge badge: badges){
            if(user.getScore()>=badge.getMinScore()){
                assert user.getBadges() != null;
                user.getBadges().add(badge.getBadgeName());
            }
        }
    }

}
