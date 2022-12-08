package com.home.MyOrganizer.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "org_user")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique = true)
    String login;

    String password;

    String name;

    @OneToOne(cascade = CascadeType.ALL)
    Contacts contacts;
    LocalDate birthDay;
    @OneToMany(mappedBy = "user")
    List<Friend> friendList;


}
