package com.example.gomaa.Service;

import com.example.gomaa.Dto.CategorySelfLoveDTO;
import com.example.gomaa.Dto.SelfLoveDTO;
import com.example.gomaa.Exception.CategoryNotFoundException;
import com.example.gomaa.Exception.UserNotFoundException;
import com.example.gomaa.Repository.CategorySelfLoveRepository;
import com.example.gomaa.Repository.SelfLoveRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.CategorySelfLove;
import com.example.gomaa.entity.SelfLove;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelfLoveService {

    private SelfLoveRepository selfLoveRepository;

    private CategorySelfLoveRepository categorySelfLoveRepository;

    private UserRepository userRepository;

    @Autowired
    public SelfLoveService(SelfLoveRepository selfLoveRepository, CategorySelfLoveRepository categorySelfLoveRepository, UserRepository userRepository) {
        this.selfLoveRepository = selfLoveRepository;
        this.categorySelfLoveRepository = categorySelfLoveRepository;
        this.userRepository = userRepository;
    }

    public SelfLoveDTO saveToFavorites(Long userId, Long categoryId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("اسم المستخدم غير موجود"));
        //when Category Not found
        CategorySelfLove category = categorySelfLoveRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("غير موجود"));

        SelfLove selfLove = SelfLove.builder()
                .user(user)
                .category(category)
                .build();

        selfLove = selfLoveRepository.save(selfLove);

        return convertToDTO(selfLove);
    }

    public List<SelfLoveDTO> getFavoritesByUser(Long userId) {
        return selfLoveRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SelfLoveDTO convertToDTO(SelfLove selfLove) {
        return SelfLoveDTO.builder()
                .id(selfLove.getId())
                .userId(selfLove.getUser().getId())
                .categorySelfLove(CategorySelfLoveDTO.builder()
                        .id(selfLove.getCategory().getId())
                        .type(selfLove.getCategory().getType())
                        .content(selfLove.getCategory().getContent())
                        .build())
                .build();
    }
}
