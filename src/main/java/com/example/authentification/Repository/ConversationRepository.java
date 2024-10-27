package com.example.authentification.Repository;

import com.example.authentification.Entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<ConversationEntity, Integer> {
    List<ConversationEntity> findByUserId(int userId);

}
