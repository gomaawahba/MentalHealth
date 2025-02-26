package com.example.gomaa.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "domv6hegb",
                "api_key", "834549333275976",
                "api_secret", "lhcBvQgQoVPMCO6oOZO1r-rcD3k",
                "secure", true
        ));
    }
}
