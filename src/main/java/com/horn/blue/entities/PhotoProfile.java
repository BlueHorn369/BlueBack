package com.horn.blue.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "PhotoProfile")

public class PhotoProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    @Column(name = "photourl", nullable = true)
    private String photourl;
    //ol
}

