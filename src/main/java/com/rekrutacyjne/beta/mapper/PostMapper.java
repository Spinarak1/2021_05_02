package com.rekrutacyjne.beta.mapper;
import com.rekrutacyjne.beta.entity.Post;
import com.rekrutacyjne.beta.entity.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDto postToPostDto(Post post);

    Post postDtoToPost(PostDto postDto);

}
