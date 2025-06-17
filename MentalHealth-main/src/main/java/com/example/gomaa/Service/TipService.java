package com.example.gomaa.Service;

import com.example.gomaa.Dto.TipDTO;
import com.example.gomaa.Repository.TipRepository;
import com.example.gomaa.entity.Tip;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipService {
    private final TipRepository tipRepository;

    public TipService(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }

    public List<TipDTO> getAllTips() {
        return tipRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<TipDTO> getTipsByCategory(String category) {
        return tipRepository.findByCategory(category).stream()
                .map(tip -> new TipDTO(tip.getCategory(), tip.getContent()))
                .collect(Collectors.toList());
    }

    public TipDTO addTip(TipDTO tipDTO) {
        Tip tip = convertToEntity(tipDTO);
        return convertToDTO(tipRepository.save(tip));
    }

    private TipDTO convertToDTO(Tip tip) {
        TipDTO dto = new TipDTO();
        dto.setCategory(tip.getCategory());
        dto.setContent(tip.getContent());
        return dto;
    }

    private Tip convertToEntity(TipDTO dto) {
        Tip tip = new Tip();
        tip.setCategory(dto.getCategory());
        tip.setContent(dto.getContent());
        return tip;
    }
}
