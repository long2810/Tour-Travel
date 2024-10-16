package com.example.my_project2.Comment.service;

import com.example.my_project2.Comment.dto.CommentDto;
import com.example.my_project2.posting.dto.PostingDto;
import com.example.my_project2.Comment.entity.Comment;
import com.example.my_project2.posting.entity.Posting;
import com.example.my_project2.Comment.repo.CommentRepository;
import com.example.my_project2.posting.repo.PostRepository;
import com.example.my_project2.service.WebService;
import com.example.my_project2.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentViewService {
    private final CommentRepository commentRepository;
    private final WebService webService;
    private final PostRepository postRepository;

    //read a comment in post
    public CommentDto readOne(Long post_id, int comment_order) {
        Posting posting = postRepository.findById(post_id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found post")
        );
        List<Comment> comments = posting.getComments();
        if(comments.size() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "posting don't have any comment");
        }
        Comment comment = Optional.of(comments.get(comment_order)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found comment with this order in this post")
        );
//        Comment commentt = commentRepository.findById(comment_id).orElseThrow(
//                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found comment")
//        );
        return CommentDto.fromEntity(comment);
    }

    //read list comment
    public List<CommentDto> readListOfOnePost(Long post_id) {
        Posting posting = postRepository.findById(post_id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found post")
        );
        PostingDto postingDto = PostingDto.fromEntity(posting);
        return postingDto.getCommentDtos();
    }


}
