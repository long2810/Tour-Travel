package com.example.my_project2.posting.service;

import com.example.my_project2.posting.dto.PostingDto;
import com.example.my_project2.posting.entity.Image_Posting;
import com.example.my_project2.posting.entity.Posting;
import com.example.my_project2.posting.repo.ImageRepository;
import com.example.my_project2.posting.repo.PostRepository;
import com.example.my_project2.service.WebService;
import com.example.my_project2.user.entity.Authority;
import com.example.my_project2.user.entity.UserEntity;
import com.example.my_project2.user.repo.UserRepo;
import com.example.my_project2.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostingService {
    private final UserRepo userRepo;
    private final UserService userService;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final WebService webService;

    //create
    public PostingDto createPost(PostingDto dto) {
        Posting posting = new Posting();
        posting.setTitle(dto.getTitle());
        posting.setContent(dto.getContent());
        posting.setLike(dto.isLike());

        UserEntity user = webService.userLogin();
        posting.setUser(user);
        return PostingDto.fromEntity(postRepository.save(posting));
    }

    //update post
    public PostingDto updatePost(Long id, PostingDto dto) {
        UserEntity curUser = webService.userLogin();
        Posting posting = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Posting does not exists!")
        );
        if(curUser.getAuthorities().stream().anyMatch(authority -> authority.getRole().equals(Authority.Role.ROLE_ADMIN) || posting.getUser().getId() == curUser.getId())) {
            posting.setTitle(dto.getTitle());
            posting.setContent(dto.getContent());
            posting.setLike(dto.isLike());
            return PostingDto.fromEntity(postRepository.save(posting));
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }
    //delete post
    @Transactional
    public void delete(Long postId) {
        UserEntity curUser = webService.userLogin();
        Posting posting = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("Posting does not exists!")
        );
        log.info(posting.getUser().getId().toString());
        if( curUser.getAuthorities().stream().anyMatch(authority -> authority.getRole().equals(Authority.Role.ROLE_ADMIN)) || posting.getUser().getId() == curUser.getId()){
            postRepository.delete(posting);
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }
    //add Image to post
    public PostingDto updatePostingImg(Long posting_id, MultipartFile image) {
        Posting posting = postRepository.findById(posting_id).orElseThrow(
                () -> new IllegalArgumentException("Posting does not exists!")
        );
        //2.파일의 업로드 위치를 결정한다
        //추천: posting_media/{postingId}
        //2.1.이미지를 업로드할 폴더가 있는지 확인 밎 생성
        String profileDir = "posting_media/" + posting_id + "/";
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //2.2. 업로드한 파일의 확장자를 추출한다
        String originalFileName = image.getOriginalFilename();
        String[] fileNameSplit = originalFileName.split("\\.");
        String extension = fileNameSplit[fileNameSplit.length - 1];
        //2.3.실제 위치에 파일을 저장한다
        //posting_media/1/image1.png
        Image_Posting imagePosting = new Image_Posting();
        imagePosting.setPosting(posting);
        imageRepository.save(imagePosting);

        String uploadPath = profileDir + "image" + imagePosting.getId() + "." + extension;
        try {
            image.transferTo(Path.of(uploadPath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //3.업로드에 성공하면, 이미지 URL을 Entity에 저장한다
        //http://localhost:8080/static/{postingId}/image1.png|jpeg

        String reqPath = "/static/" + posting_id + "/image" + imagePosting.getId() + "." + extension;
        imagePosting.setLink(reqPath);

        posting.getImages().add(imagePosting);
        return PostingDto.fromEntity(postRepository.save(posting));
    }

}
