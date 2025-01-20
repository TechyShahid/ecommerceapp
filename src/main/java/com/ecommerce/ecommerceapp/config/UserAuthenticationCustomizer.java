package com.ecommerce.ecommerceapp.config;

import com.ecommerce.ecommerceapp.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;

@Configuration
public class UserAuthenticationCustomizer implements Customizer<User> {
    @Override
    public void customize(User user) {

    }
}
