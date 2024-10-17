package com.example.travel.message.repo;

import com.example.travel.authentication.user.entity.UserEntity;
import com.example.travel.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    @Query(value = "SELECT DISTINCT m FROM Message m WHERE "+
            "(m.sender = :user1 AND m.receiver = :user2) OR "+
            "(m.sender = :user2 AND m.receiver = :user1) "+
            "ORDER BY m.createAt ASC")
    List<Message> chatMessages(@Param("user1") UserEntity user1,
                               @Param("user2") UserEntity user2);
    @Query("SELECT COUNT(DISTINCT m.sender) FROM Message m "+
            "WHERE m.receiver = :user " +
            "GROUP BY m.sender")
    Integer countFriendMes(@Param("user") UserEntity user);

    List<Message> findBySenderAndReceiver(UserEntity sender, UserEntity receiver);
}
