package com.example.testsecurity2dbthymeleaf.entity;

import com.example.testsecurity2dbthymeleaf.repository.UserActionRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_ACTIONS")
public class UserAction {

    @Id
    @Column(name = "date_action")
    private LocalDateTime date_action;

    @Column(name = "description")
    private String description;

    public UserAction (String description){
        this.date_action = LocalDateTime.now();
        this.description = description;
    }

}
