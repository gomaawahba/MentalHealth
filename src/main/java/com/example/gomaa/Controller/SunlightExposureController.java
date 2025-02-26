package com.example.gomaa.Controller;

import com.example.gomaa.Dto.SunlightExposureDTO;
import com.example.gomaa.Service.SunlightExposureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sunlight")
public class SunlightExposureController {

    private SunlightExposureService sunlightExposureService;

    @Autowired
    public SunlightExposureController(SunlightExposureService sunlightExposureService) {
        this.sunlightExposureService = sunlightExposureService;
    }

    @PostMapping
    public ResponseEntity<String> saveSunlightExposure(@RequestBody SunlightExposureDTO dto) {
        sunlightExposureService.saveSunlightExposure(dto);
        return ResponseEntity.ok("تم حفظ بيانات التعرض لضوء الشمس بنجاح");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Object>> getSunlightExposures(@PathVariable Long userId) {
        return ResponseEntity.ok(sunlightExposureService.getSunlightExposuresByUser(userId));
    }

}
