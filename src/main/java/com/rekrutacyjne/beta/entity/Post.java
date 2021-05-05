package com.rekrutacyjne.beta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    @Id
    private Integer id;
    private String title;
    private String body;
    @JsonIgnore
    private Integer userId;

}
