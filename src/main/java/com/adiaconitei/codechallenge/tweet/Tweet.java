package com.adiaconitei.codechallenge.tweet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tweets")
public class Tweet {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "uuid2"
    )
    @Column(name = "id")
    private String id;

    @Column(name = "user")
    private String user;

    @Column(name = "content")
    private String content;

    @Column(name = "location")
    private String location;

    @Column(name = "valid")
    private Boolean valid;

}
