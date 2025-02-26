package com.example.gomaa.Controller;

import com.example.gomaa.Dto.TipDTO;
import com.example.gomaa.Service.TipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tips")
public class TipController {
    private final TipService tipService;

    public TipController(TipService tipService) {
        this.tipService = tipService;
    }

    @GetMapping
    public ResponseEntity<List<TipDTO>> getAllTips() {
        return ResponseEntity.ok(tipService.getAllTips());
    }

    //http://localhost:8080/api/tips/category/التغذية الصحية
    @GetMapping("/category/{category}")
    public ResponseEntity<List<String>> getTipsByCategory(@PathVariable String category) {
        List<String> tipsContent = tipService.getTipsByCategory(category)
                .stream()
                .map(tipDTO -> tipDTO.getContent()) // استخراج المحتوى فقط
                .collect(Collectors.toList());
        return ResponseEntity.ok(tipsContent);
    }

    @PostMapping
    public ResponseEntity<TipDTO> addTip(@RequestBody TipDTO tipDTO) {
        return ResponseEntity.ok(tipService.addTip(tipDTO));
    }
}
