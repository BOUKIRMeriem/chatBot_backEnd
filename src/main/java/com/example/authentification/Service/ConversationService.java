package com.example.authentification.Service;
import com.example.authentification.Repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    // Other methods...

    public boolean deleteConversation(int id) {
        if (conversationRepository.existsById(id)) {
            conversationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
