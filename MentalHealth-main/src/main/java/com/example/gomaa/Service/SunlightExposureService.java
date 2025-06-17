package com.example.gomaa.Service;

import com.example.gomaa.Dto.SunlightExposureDTO;
import com.example.gomaa.Repository.SunlightExposureRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.SunlightExposure;
import com.example.gomaa.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SunlightExposureService {

    private SunlightExposureRepository sunlightExposureRepository;

    private  UserRepository userRepository;

    public SunlightExposureService(SunlightExposureRepository sunlightExposureRepository, UserRepository userRepository) {
        this.sunlightExposureRepository = sunlightExposureRepository;
        this.userRepository = userRepository;
    }

    public SunlightExposure saveSunlightExposure(SunlightExposureDTO dto) {
        Users user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("اسم المستخدم غير موجود"));

        String description = "تعرضت لضوء الشمس لمدة " + dto.getDuration() + " دقائق";

        SunlightExposure sunlightExposure = SunlightExposure.builder()
                .user(user)
                .date(dto.getDate())
                .duration(dto.getDuration())
                .description(description)
                .build();

        return sunlightExposureRepository.save(sunlightExposure);
    }

    public List<Object> getSunlightExposuresByUser(Long userId) {
        return sunlightExposureRepository.findByUserId(userId)
                .stream()
                .map(exposure -> {
                    return new Object() {
                        public final String description = exposure.getDescription();
                        public final String date = exposure.getDate().toString();
                    };
                })
                .collect(Collectors.toList());
    }
}
