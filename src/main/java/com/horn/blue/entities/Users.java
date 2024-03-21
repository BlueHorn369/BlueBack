package com.horn.blue.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table (name = "Users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userID;

    @Column(name = "userName", nullable = false, length = 100)
    private String userName;

    @Column(name = "userLastName", nullable = false, length = 100)
    private String userLastName;

    @Column(name = "userEmail", nullable = false, length = 100)
    private String userEmail;

    @Column(name = "userGender", nullable = false, length = 100)
    private String userGender;

    @Column(name = "userPassword", nullable = false, length = 100)
    private String userPassword;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "userBirthdate", nullable = false)
    private Date userBirthdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "registerDate", nullable = false)
    private LocalDateTime registerDate;

    /* @Column(name = "userActive", nullable = false, length = 100)
     private String userActive;*/

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userPhotoID")
//    @JsonIgnore
//    private PhotoProfile photoProfile;

    private Boolean userActive;

}

