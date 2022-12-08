package com.home.MyOrganizer.services;

import com.home.MyOrganizer.model.Contacts;
import com.home.MyOrganizer.model.User;
import com.home.MyOrganizer.repository.FriendRepository;
import com.home.MyOrganizer.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContactsService {
    UserRepository userRepository;
    FriendRepository friendRepository;

    public Contacts createUserContact(Long userId, Contacts contacts) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setContacts(contacts);
                    return userRepository.save(user);
                })
                .map(User::getContacts)
                .orElse(null);
    }

    public Contacts createFriendContact(Long friendId, Contacts contacts) {
        return friendRepository.findById(friendId)
                .map(friend -> {
                    friend.setContacts(contacts);
                    friendRepository.save(friend);
                    return contacts;
                })
                .orElse(null);
    }

}
