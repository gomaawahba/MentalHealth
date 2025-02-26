package com.example.gomaa.Service;

import com.example.gomaa.Dto.RoutineDTO;
import com.example.gomaa.Dto.RoutineDetailDTO;
import com.example.gomaa.Dto.UserRoutineDTO;
import com.example.gomaa.Repository.RoutineRepository;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.Routine;
import com.example.gomaa.entity.RoutineDetail;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineService {

    private final UserRepository userRepository;
    private final RoutineRepository routineRepository;

    @Autowired
    public RoutineService(UserRepository userRepository, RoutineRepository routineRepository) {
        this.userRepository = userRepository;
        this.routineRepository = routineRepository;
    }

    public String addRoutine(Long userId, RoutineDTO routineDTO) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("المستخدم غير موجود"));

        // إنشاء الروتين
        Routine routine = new Routine();
        routine.setUser(user);
        routine.setRoutineType(routineDTO.getRoutineType());

        // إضافة التفاصيل
        List<RoutineDetail> details = routineDTO.getDetails().stream().map(dto -> {
            RoutineDetail detail = new RoutineDetail();
            detail.setRoutine(routine);
            detail.setDetail(dto.getDetail());
            return detail;
        }).collect(Collectors.toList());

        routine.setDetails(details);
        routineRepository.save(routine);

        return "تم حفظ الروتين الخاص بك، " + user.getName();
    }


    // استرجاع جميع الروتينات الخاصة بالمستخدم
    public UserRoutineDTO getUserRoutines(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("المستخدم غير موجود"));

        List<Routine> routines = routineRepository.findByUserId(userId);

        List<RoutineDTO> routineDTOs = routines.stream().map(routine -> {
            RoutineDTO routineDTO = new RoutineDTO();
            routineDTO.setRoutineType(routine.getRoutineType());

            List<RoutineDetailDTO> detailDTOs = routine.getDetails().stream().map(detail -> {
                RoutineDetailDTO detailDTO = new RoutineDetailDTO();
                detailDTO.setDetail(detail.getDetail());
                return detailDTO;
            }).collect(Collectors.toList());

            routineDTO.setDetails(detailDTOs);
            return routineDTO;
        }).collect(Collectors.toList());

        UserRoutineDTO userRoutineDTO = new UserRoutineDTO();
        userRoutineDTO.setUsername(user.getName());
        userRoutineDTO.setRoutines(routineDTOs);

        return userRoutineDTO;
    }

}
