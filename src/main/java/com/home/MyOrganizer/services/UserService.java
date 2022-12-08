package com.home.MyOrganizer.services;

import com.home.MyOrganizer.exception.UserAlreadyExistsException;
import com.home.MyOrganizer.model.Contacts;
import com.home.MyOrganizer.model.Dto.UserDto;
import com.home.MyOrganizer.model.Friend;
import com.home.MyOrganizer.model.User;
import com.home.MyOrganizer.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    public UserDto createUser(UserDto user){
    User userToSave = User.builder()
            .login(user.getLogin())
            .password(user.getPassword())
            .birthDay(user.getBirthDay())
            .contacts(user.getContacts())
            .friendList(user.getFriendList())
            .build();
    User savedUser = userRepository.save(userToSave);
        return UserDto.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .login(savedUser.getLogin())
                .birthDay(savedUser.getBirthDay())
                .contacts(savedUser.getContacts())
                .friendList(savedUser.getFriendList())
                .build();
    }


    public List<UserDto> getUser() {
        List<User> list = userRepository.findAll();
        List<UserDto> dtoList  = new ArrayList<>();
        for (User elem: list) {
           dtoList.add(UserDto.builder()
                    .id(elem.getId())
                    .name(elem.getName())
                    .login(elem.getLogin())
                    .birthDay(elem.getBirthDay())
                    .contacts(elem.getContacts())
                    .friendList(elem.getFriendList())
                    .build());
        }
        return dtoList;
    }

    public List<Friend> getFriendByUserId(long userId) {
        User tmpUser;
        if(!userRepository.exists(Example.of((tmpUser = userRepository.getById(userId))))){
            throw new UserAlreadyExistsException("такого пользователя нету");
        }
        return tmpUser.getFriendList();
    }

    public Map<User, Contacts> getUserContacts() {
        List<User> list = userRepository.findAll();
        Map<User,Contacts> map = new HashMap<>();
        for (User user : list) {
            map.put(user,user.getContacts());
        }
        return map;
    }

    public Map<Friend,Contacts> getFriendContactsByUserId(long userId) {
        User tmpUser;
        Map<Friend,Contacts> map = new HashMap<>();
        if(!userRepository.exists(Example.of((tmpUser = userRepository.getById(userId))))){
            throw new UserAlreadyExistsException("такого пользователя нету");
        }
        List<Friend> list = tmpUser.getFriendList();
        for (Friend friend: list) {
           map.put(friend,friend.getContacts()) ;
        }
        return map;
    }
}
