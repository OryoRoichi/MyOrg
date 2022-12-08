package com.home.MyOrganizer.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class Contacts {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    Long id;
    String adress;
    @ElementCollection
    List<String> phones;
    @ElementCollection
    List<String> email;
    @ElementCollection
    List<String> messengers;
}
