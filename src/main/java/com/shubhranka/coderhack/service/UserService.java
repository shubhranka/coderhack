package com.shubhranka.coderhack.service;

import com.shubhranka.coderhack.dto.CreateUserDto;
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
    public User createUser(CreateUserDto userDto) {

        User user = new User(userDto.getUserId(),userDto.getUserName());
        validateUser(user);
        userRepository.save(user);

        return user;
    }

    private void validateUser(User user) {
        if(checkUserAlreadyExists(user.getUserId())) throw new UserException("User already exists", HttpStatus.CONFLICT);

    }

    private boolean checkUserAlreadyExists(String userId) {
        User foundUser = userRepository.findByUserId(userId);
        return foundUser != null;
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


    private void validateScore(byte score) {
        if(score<0)
            throw new UserException("Score cannot be negative", HttpStatus.BAD_REQUEST);
        if(score>100)
            throw new UserException("Score cannot be greater than 100", HttpStatus.BAD_REQUEST);
    }

    public User updateUser(String id, UpdateUserDto user) {
        if(!checkUserAlreadyExists(id)) {
            throw new UserException("User does not exist", HttpStatus.NOT_FOUND);
        }
        validateScore(user.getScore());
        User foundUser = userRepository.findByUserId(id);
        foundUser.setScore(user.getScore());
        updateBadges(foundUser);
        userRepository.save(foundUser);
        return foundUser;
    }

    public User getUser(String userId) {
        User user = userRepository.findByUserId(userId);
        if(user == null) throw new UserException("User does not exist", HttpStatus.NOT_FOUND);
        return user;
    }

    public User deleteUser(String userId) {
        User user = userRepository.findByUserId(userId);
        if(user == null) throw new UserException("User does not exist", HttpStatus.NOT_FOUND);
        userRepository.delete(user);
        return user;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        users.sort((o1, o2) -> o2.getScore()-o1.getScore());
        return users;
    }


}
