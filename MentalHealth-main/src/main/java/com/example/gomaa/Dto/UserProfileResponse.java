package com.example.gomaa.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private String name;
    private String email;
    private String country;
    private String birthdate;  // يمكن تخزين التاريخ كنص بتنسيق yyyy-MM-dd
    private String avatar;
    private String coverImage;
}
