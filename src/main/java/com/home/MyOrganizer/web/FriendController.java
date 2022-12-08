package com.home.MyOrganizer.web;

import com.home.MyOrganizer.model.Friend;
import com.home.MyOrganizer.repository.FriendRepository;
import com.home.MyOrganizer.services.FriendService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendController {
    FriendService service;

    @PostMapping("/create")
    public ResponseEntity<Friend> createFriend(@RequestBody Friend friend){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createFriend(friend));
    }

}
