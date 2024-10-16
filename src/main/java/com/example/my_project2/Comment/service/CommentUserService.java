package com.example.my_project2.Comment.service;

import com.example.my_project2.Comment.dto.CommentDto;
import com.example.my_project2.Comment.entity.Comment;
import com.example.my_project2.Comment.repo.CommentRepository;
import com.example.my_project2.posting.entity.Posting;
import com.example.my_project2.posting.repo.PostRepository;
import com.example.my_project2.service.WebService;
import com.example.my_project2.user.entity.Authority;
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
public class CommentUserService {
    private final CommentRepository commentRepository;
    private final WebService webService;
    private final PostRepository postRepository;

    //create
    public CommentDto createComment(Long post_id, String content) {
        UserEntity curUser = webService.userLogin();
        Posting posting = postRepository.findById(post_id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found post")
        );

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setOwnerName(curUser.getUsername());
        comment.setPosting(posting);
        posting.getComments().add(comment);
        return CommentDto.fromEntity(commentRepository.save(comment));
    }

    //update comment
    public CommentDto update(Long post_id, int comment_order, String content) {
        UserEntity curUser = webService.userLogin();

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

        if (curUser.getUsername().equals(comment.getOwnerName())) {
            comment.setContent(content);
            return CommentDto.fromEntity(commentRepository.save(comment));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    //delete
    public void delete(Long comment_id) {
        UserEntity curUser = webService.userLogin();
        Comment comment = commentRepository.findById(comment_id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found comment")
        );
        log.info(curUser.getUsername());
        log.info(comment.getOwnerName());
        if (curUser.getUsername().equals(comment.getOwnerName()) || curUser.getAuthorities().contains(Authority.Role.ROLE_ADMIN)) {
            commentRepository.delete(comment);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
