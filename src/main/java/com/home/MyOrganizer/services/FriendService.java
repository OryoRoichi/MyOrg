package com.home.MyOrganizer.services;

import com.home.MyOrganizer.exception.UserAlreadyExistsException;
import com.home.MyOrganizer.model.Friend;
import com.home.MyOrganizer.repository.FriendRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendService {
    FriendRepository friendRepository;

    public Friend createFriend(Friend friend) {
        if(friendRepository.exists(Example.of(friend))){
            throw  new UserAlreadyExistsException("УЖЕ ЕСТЬ");
        }
        return friendRepository.save(friend);
    }


}
