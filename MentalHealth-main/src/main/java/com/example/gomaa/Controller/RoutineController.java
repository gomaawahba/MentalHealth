package com.example.gomaa.Controller;

import com.example.gomaa.Dto.RoutineDTO;
import com.example.gomaa.Dto.UserRoutineDTO;
import com.example.gomaa.Service.RoutineService;
import com.example.gomaa.entity.Routine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routines")
public class RoutineController {
    private final RoutineService routineService;

    @Autowired
    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    // إضافة روتين جديد
    @PostMapping("/{userId}")
    public ResponseEntity<String> addRoutine(@PathVariable Long userId, @RequestBody RoutineDTO routineDTO) {
        String message = routineService.addRoutine(userId, routineDTO);
        return ResponseEntity.ok(message);
    }


    // استرجاع جميع الروتينات الخاصة بالمستخدم
    @GetMapping("/{userId}")
    public ResponseEntity<UserRoutineDTO> getUserRoutines(@PathVariable Long userId) {
        return ResponseEntity.ok(routineService.getUserRoutines(userId));
    }
}