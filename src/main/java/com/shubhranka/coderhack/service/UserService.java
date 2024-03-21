package com.shubhranka.coderhack.service;

import com.shubhranka.coderhack.dto.UpdateUserDto;
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

    private void validateScore(byte score) {
        if(score<0)
            throw new UserException("Score cannot be negative", HttpStatus.BAD_REQUEST);
        if(score>100)
            throw new UserException("Score cannot be greater than 100", HttpStatus.BAD_REQUEST);
    }

    private void validateUser(User user) {
        validateScore(user.getScore());
        if(checkUserAlreadyExists(user.getUserId())) throw new UserException("User already exists", HttpStatus.CONFLICT);

    }

    private boolean checkUserAlreadyExists(String userId) {
        User foundUser = userRepository.findByUserId(userId);
        return foundUser == null;
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


    public User updateUser(UpdateUserDto user) {
        if(!checkUserAlreadyExists(user.getUserId())) {
            throw new UserException("User does not exist", HttpStatus.NOT_FOUND);
        }
        validateScore(user.getScore());
        User foundUser = userRepository.findByUserId(user.getUserId());
        foundUser.setScore(user.getScore());
        updateBadges(foundUser);
        userRepository.save(foundUser);
        return foundUser;
    }


}
