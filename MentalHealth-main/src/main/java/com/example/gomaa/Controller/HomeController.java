package com.example.gomaa.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<List<String>> getHomeFeatures() {
        List<String> features = List.of(
                "تتبع المزاج",
                "تمارين التنفس",
                "التأمل واليقظة الذهنية",
                "تحديد الأهداف وتتبع العادات",
                "الامتنان",
                "تعقب التمارين الرياضية",
                "النوم",
                "مكتبة الفيديو",
                "حب الذات",
                "الموسيقى",
                "التغذية والتمارين الرياضية",
                "محادثة الذكاء الاصطناعي",
                "الملاحظات الشخصية",
                "الأفكار السلبية",
                "إشعارات وتنبيهات",
                "مشاركة الإنجازات",
                "الألعاب والإنجازات",
                "تخصيص العادات والروتين",
                "التعرض لأشعة الشمس وفيتامين D"
        );
        return ResponseEntity.ok(features);
    }
}


