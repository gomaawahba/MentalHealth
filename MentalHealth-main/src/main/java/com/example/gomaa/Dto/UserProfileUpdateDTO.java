package com.example.gomaa.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileUpdateDTO {
    @NotBlank(message = "الاسم مطلوب")
    private String name;

    @NotNull(message = "تاريخ الميلاد مطلوب")
    private Date birthdate;

    @NotBlank(message = "الدولة مطلوبة")
    private String country;
}
