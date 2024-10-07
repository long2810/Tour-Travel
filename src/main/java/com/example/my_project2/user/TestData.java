package com.example.my_project2.user;

import com.example.my_project2.Comment.entity.Comment;
import com.example.my_project2.Comment.repo.CommentRepository;
import com.example.my_project2.friend.entity.Friend;
import com.example.my_project2.friend.repo.FriendRepo;
import com.example.my_project2.posting.entity.Posting;
import com.example.my_project2.posting.repo.PostRepository;
import com.example.my_project2.user.entity.Authority;
import com.example.my_project2.user.entity.UserEntity;
import com.example.my_project2.user.repo.AuthorityRepo;
import com.example.my_project2.user.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class TestData {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final AuthorityRepo authorityRepo;
    private final FriendRepo friendRepo;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    public TestData(UserRepo userRepo, PasswordEncoder encoder, AuthorityRepo authorityRepo, FriendRepo friendRepo,PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.authorityRepo = authorityRepo;
        this.friendRepo = friendRepo;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.fixUser();
        this.fixPost();
        this.fixComment();
    }

    public void fixUser(){
        Authority authorityAdmin = authorityRepo.findByRole(Authority.Role.ROLE_ADMIN).orElseThrow();
        Authority authorityUser = authorityRepo.findByRole(Authority.Role.ROLE_USER).orElseThrow();
        Authority authorityViewer = authorityRepo.findByRole(Authority.Role.ROLE_VIEWER).orElseThrow();
        Authority authorityOwner = authorityRepo.findByRole(Authority.Role.ROLE_OWNER).orElseThrow();

        UserEntity admin = UserEntity.builder()
                .username("luna010209")
                .password(encoder.encode("1234"))
                .name("Luna Do")
                .email("luna@gmail.com")
                .phone("01234")
                .birthday(LocalDate.of(2001, 2, 9))
                .country("Vietnam")
                .school("Sogang university")
                .workplace("Sogang")
                .interests(UserEntity.Interests.DANCE)
                .profileImg("/static/visual/user.png")
                .build();
        admin.getAuthorities().add(authorityAdmin);
        admin.getAuthorities().add(authorityUser);
        admin.getAuthorities().add(authorityOwner);
        admin.getAuthorities().add(authorityViewer);
        userRepo.save(admin);

        UserEntity user2 = UserEntity.builder()
                .username("user2")
                .password(encoder.encode("1234"))
                .name("Lina")
                .email("user2@gmail.com")
                .phone("012456")
                .birthday(LocalDate.of(2000, 12, 9))
                .country("Korea")
                .school("X university")
                .workplace("X company")
                .interests(UserEntity.Interests.MUSIC)
                .profileImg("/static/visual/user.png")
                .build();
        user2.getAuthorities().add(authorityUser);
        user2.getAuthorities().add(authorityOwner);
        user2.getAuthorities().add(authorityViewer);
        userRepo.save(user2);

        UserEntity user3 = UserEntity.builder()
                .username("user3")
                .password(encoder.encode("1234"))
                .name("Cian")
                .email("user3@gmail.com")
                .phone("01243")
                .birthday(LocalDate.of(2005, 5, 12))
                .country("Taiwan")
                .school("T university")
                .workplace("X company")
                .interests(UserEntity.Interests.MUSIC)
                .profileImg("/static/visual/user.png")
                .build();
        user3.getAuthorities().add(authorityUser);
        user3.getAuthorities().add(authorityOwner);
        user3.getAuthorities().add(authorityViewer);
        userRepo.save(user3);

        UserEntity user4 = UserEntity.builder()
                .username("user4")
                .password(encoder.encode("1234"))
                .profileImg("/static/visual/user.png")
                .build();
        user4.getAuthorities().add(authorityViewer);
        userRepo.save(user4);

        UserEntity user5 = UserEntity.builder()
                .username("user5")
                .password(encoder.encode("1234"))
                .name("Clara")
                .email("user5@gmail.com")
                .phone("01243")
                .profileImg("/static/visual/user.png")
                .build();
        user5.getAuthorities().add(authorityUser);
        user5.getAuthorities().add(authorityViewer);
        userRepo.save(user5);

        UserEntity user6 = UserEntity.builder()
                .username("user6")
                .password(encoder.encode("1234"))
                .name("Alex")
                .email("user6@gmail.com")
                .phone("01244")
                .profileImg("/static/visual/user.png")
                .build();
        user6.getAuthorities().add(authorityUser);
        user6.getAuthorities().add(authorityViewer);
        userRepo.save(user6);

        UserEntity user7 = UserEntity.builder()
                .username("user7")
                .password(encoder.encode("1234"))
                .name("Jordan")
                .email("user7@gmail.com")
                .phone("01245")
                .profileImg("/static/visual/user.png")
                .build();
        user7.getAuthorities().add(authorityUser);
        user7.getAuthorities().add(authorityViewer);
        userRepo.save(user7);

        UserEntity user8 = UserEntity.builder()
                .username("user8")
                .password(encoder.encode("1234"))
                .name("Taylor")
                .email("user8@gmail.com")
                .phone("01246")
                .profileImg("/static/visual/user.png")
                .build();
        user8.getAuthorities().add(authorityUser);
        user8.getAuthorities().add(authorityViewer);
        userRepo.save(user8);

        UserEntity user9 = UserEntity.builder()
                .username("user9")
                .password(encoder.encode("1234"))
                .name("Morgan")
                .email("user9@gmail.com")
                .phone("01247")
                .profileImg("/static/visual/user.png")
                .build();
        user9.getAuthorities().add(authorityUser);
        user9.getAuthorities().add(authorityViewer);
        userRepo.save(user9);

        UserEntity user10 = UserEntity.builder()
                .username("user10")
                .password(encoder.encode("1234"))
                .name("Riley")
                .email("user10@gmail.com")
                .phone("01248")
                .profileImg("/static/visual/user.png")
                .build();
        user10.getAuthorities().add(authorityUser);
        user10.getAuthorities().add(authorityViewer);
        userRepo.save(user10);

        UserEntity user11 = UserEntity.builder()
                .username("user11")
                .password(encoder.encode("1234"))
                .name("Sam")
                .email("user11@gmail.com")
                .phone("01249")
                .profileImg("/static/visual/user.png")
                .build();
        user11.getAuthorities().add(authorityUser);
        user11.getAuthorities().add(authorityViewer);
        userRepo.save(user11);

        UserEntity user12 = UserEntity.builder()
                .username("user12")
                .password(encoder.encode("1234"))
                .name("Jamie")
                .email("user12@gmail.com")
                .phone("01250")
                .profileImg("/static/visual/user.png")
                .build();
        user12.getAuthorities().add(authorityUser);
        user12.getAuthorities().add(authorityViewer);
        userRepo.save(user12);

        UserEntity user13 = UserEntity.builder()
                .username("user13")
                .password(encoder.encode("1234"))
                .name("Casey")
                .email("user13@gmail.com")
                .phone("01251")
                .profileImg("/static/visual/user.png")
                .build();
        user13.getAuthorities().add(authorityUser);
        user13.getAuthorities().add(authorityViewer);
        userRepo.save(user13);

        UserEntity user14 = UserEntity.builder()
                .username("user14")
                .password(encoder.encode("1234"))
                .name("Drew")
                .email("user14@gmail.com")
                .phone("01252")
                .profileImg("/static/visual/user.png")
                .build();
        user14.getAuthorities().add(authorityUser);
        user14.getAuthorities().add(authorityViewer);
        userRepo.save(user14);

        UserEntity user15 = UserEntity.builder()
                .username("user15")
                .password(encoder.encode("1234"))
                .name("Robin")
                .email("user15@gmail.com")
                .phone("01253")
                .profileImg("/static/visual/user.png")
                .build();
        user15.getAuthorities().add(authorityUser);
        user15.getAuthorities().add(authorityViewer);
        userRepo.save(user15);

        Friend friend23 = Friend.builder()
                .user1(user2)
                .user2(user3)
                .status(Friend.Status.Friend)
                .build();
        friendRepo.save(friend23);

        Friend friend28 = Friend.builder()
                .user1(user2)
                .user2(user8)
                .status(Friend.Status.Friend)
                .build();
        friendRepo.save(friend28);

        Friend friend25 = Friend.builder()
                .user1(user2)
                .user2(user5)
                .status(Friend.Status.Friend)
                .build();
        friendRepo.save(friend25);
        Friend friend26 = Friend.builder()
                .user1(user2)
                .user2(user6)
                .status(Friend.Status.Friend)
                .build();
        friendRepo.save(friend26);

        Friend friend27 = Friend.builder()
                .user1(user2)
                .user2(user7)
                .status(Friend.Status.Friend)
                .build();
        friendRepo.save(friend27);

        Friend friend36 = Friend.builder()
                .user1(user3)
                .user2(user6)
                .status(Friend.Status.Friend)
                .build();
        friendRepo.save(friend36);

        Friend friend35 = Friend.builder()
                .user1(user3)
                .user2(user5)
                .status(Friend.Status.Friend)
                .build();
        friendRepo.save(friend35);
    }
    public void fixPost() {
        UserEntity user1 = userRepo.findById(1L)
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));
        UserEntity user2 = userRepo.findById(2L)
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));
        UserEntity user3 = userRepo.findById(3L)
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));

        Posting posting1 = Posting.builder()
                .title("This is post one")
                .content("This post is fantasic")
                .like(true)
                .user(user1)
                .build();
        postRepository.save(posting1);
        Posting posting2 = Posting.builder()
                .title("This is post one")
                .content("This post is fantasic")
                .like(true)
                .user(user1)
                .build();
        postRepository.save(posting2);
        Posting posting3 = Posting.builder()
                .title("This is post one")
                .content("This post is fantasic")
                .like(true)
                .user(user1)
                .build();
        postRepository.save(posting3);

        Posting posting4 = Posting.builder()
                .title("This is post 2")
                .content("This post is fantasic")
                .like(true)
                .user(user2)
                .build();
        postRepository.save(posting4);
        Posting posting5 = Posting.builder()
                .title("This is post 2")
                .content("This post is fantasic")
                .like(true)
                .user(user2)
                .build();
        postRepository.save(posting5);

        Posting posting6 = Posting.builder()
                .title("This is post 2")
                .content("This post is fantasic")
                .like(true)
                .user(user2)
                .build();
        postRepository.save(posting6);

        Posting posting7 = Posting.builder()
                .title("This is post 3")
                .content("This post is fantasic")
                .like(true)
                .user(user3)
                .build();
        postRepository.save(posting7);
        Posting posting8 = Posting.builder()
                .title("This is post 3")
                .content("This post is fantasic")
                .like(true)
                .user(user3)
                .build();
        postRepository.save(posting8);
        Posting posting9 = Posting.builder()
                .title("This is post 3")
                .content("This post is fantasic")
                .like(true)
                .user(user3)
                .build();
        postRepository.save(posting9);
    }

    public void fixComment() {
        UserEntity user1 = userRepo.findById(1L)
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));
        UserEntity user2 = userRepo.findById(3L)
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));
        UserEntity user3 = userRepo.findById(3L)
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));

        Posting posting1 = postRepository.findById(1L)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Posting posting2 = postRepository.findById(2L)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Posting posting3 = postRepository.findById(3L)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Comment comment1 = Comment.builder()
                .content("this is comment for post1")
                .ownerName(user1.getUsername())
                .posting(posting2)
                .build();
        commentRepository.save(comment1);

        Comment comment2 = Comment.builder()
                .content("this is comment for post1")
                .ownerName(user1.getUsername())
                .posting(posting2)
                .build();
        commentRepository.save(comment2);

        Comment comment3 = Comment.builder()
                .content("this is comment for post1")
                .ownerName(user1.getUsername())
                .posting(posting2)
                .build();
        commentRepository.save(comment3);

        Comment comment4 = Comment.builder()
                .content("this is comment for post1")
                .ownerName(user1.getUsername())
                .posting(posting2)
                .build();
        commentRepository.save(comment4);

        Comment comment5 = Comment.builder()
                .content("this is comment for post1")
                .ownerName(user1.getUsername())
                .posting(posting2)
                .build();
        commentRepository.save(comment5);

        Comment comment6 = Comment.builder()
                .content("this is comment for post1")
                .ownerName(user1.getUsername())
                .posting(posting2)
                .build();
        commentRepository.save(comment6);
    }
}
