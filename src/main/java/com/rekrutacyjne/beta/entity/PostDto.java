package com.rekrutacyjne.beta.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Integer id;
    private String title;
    private String body;
    @JsonIgnore
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDto postDto = (PostDto) o;
        return Objects.equals(id, postDto.id) &&
                Objects.equals(title, postDto.title) &&
                Objects.equals(body, postDto.body) &&
                Objects.equals(userId, postDto.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, userId);
    }
}
