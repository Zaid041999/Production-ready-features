package com.codingshuttle.zaid.prod_ready_features.prod_ready_features.services;

import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.entities.PostEntity;
import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import com.codingshuttle.zaid.prod_ready_features.prod_ready_features.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPost() {

        return postRepository
                .findAll()
                .stream()
                .map(PostEntity->modelMapper.map(PostEntity,PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
       PostEntity postEntity = modelMapper.map(inputPost,PostEntity.class);
       return modelMapper.map(postRepository.save(postEntity),PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity =postRepository
                                 .findById(postId).
                                 orElseThrow(()-> new ResourceNotFoundException("Post Not Found With Id:"+postId));
        return modelMapper.map(postEntity,PostDTO.class);
    }
}
