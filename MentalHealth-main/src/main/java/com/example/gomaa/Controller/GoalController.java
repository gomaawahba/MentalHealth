package com.example.gomaa.Controller;


import com.example.gomaa.Dto.GoalDTO;
import com.example.gomaa.Service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    //save goal

    @PostMapping("/save")
    public ResponseEntity<String> saveGoal(@RequestBody GoalDTO dto) {
        goalService.saveGoal(dto);
        return ResponseEntity.ok("✅ تم تسجيل الهدف بنجاح!");
    }

    //get all goals for belonge to user
    @GetMapping("/user/{userId}")
    public List<GoalDTO> getGoalsByUser(@PathVariable Long userId) {
        return goalService.getGoalsByUser(userId);
    }

    //goal complete
    @PutMapping("/complete/{goalId}")
    public ResponseEntity<String> completeGoal(@PathVariable Long goalId) {
        goalService.markGoalCompleted(goalId);
        return ResponseEntity.ok("""
                🎉 مبروك حققت هدفك!
                بكل إصرار واجتهاد إحنا فخورين بيك جدا،
                خليك دايما واثق في نفسك،
                واستمر في التقدم نحو أهداف جديدة.
                كل خطوة بتاخذها بتقربك أكثر من النجاح 🥇
                """);
    }
    //goal Not Complete


    @PutMapping("/not-complete/{goalId}")
    public ResponseEntity<String> notCompleteGoal(@PathVariable Long goalId) {
        goalService.markGoalNotCompleted(goalId);
        return ResponseEntity.ok("❌ الهدف لم يكتمل، لا تيأس! حاول مرة أخرى 💪");
    }
}
