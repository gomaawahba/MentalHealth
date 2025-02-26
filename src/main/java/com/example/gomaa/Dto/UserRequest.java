package com.example.gomaa.Dto;

import jakarta.validation.constraints.*;

import java.util.Date;

public class UserRequest {
    @NotBlank(message = "الاسم مطلوب")
    @Size(min = 3, message = "يجب أن يكون الاسم على الأقل 3 أحرف")
    private String name;
    @NotNull(message = "تاريخ الميلاد مطلوب")
    private Date birthdate;
    @NotBlank(message = "البريد الإلكتروني مطلوب")
    @Email(message = "البريد الإلكتروني غير صالح")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "البريد الإلكتروني يجب أن يكون Gmail فقط")
    private String email;
    @NotBlank(message = "الدولة مطلوبة")
    private String country;
    @NotBlank(message = "كلمة المرور مطلوبة")
    @Size(min = 8, message = "يجب أن تحتوي كلمة المرور على الأقل 8 أحرف")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
//            message = "يجب أن تحتوي كلمة المرور على الأقل على حرف واحد ورقم واحد")
    private String password;
    private String avatar;
    private String coverImage;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
