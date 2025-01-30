package com.ecommerce.ecommerceapp.controller;

import com.ecommerce.ecommerceapp.aop.LogExecutionTime;
import com.ecommerce.ecommerceapp.config.jwt.JwtUtils;
import com.ecommerce.ecommerceapp.config.service.UserDetailsImpl;
import com.ecommerce.ecommerceapp.dao.RoleRepository;
import com.ecommerce.ecommerceapp.dao.UserRepository;
import com.ecommerce.ecommerceapp.entity.ERole;
import com.ecommerce.ecommerceapp.entity.Role;
import com.ecommerce.ecommerceapp.entity.User;
import com.ecommerce.ecommerceapp.payload.request.LoginRequest;
import com.ecommerce.ecommerceapp.payload.request.SignupRequest;
import com.ecommerce.ecommerceapp.payload.response.MessageResponse;
import com.ecommerce.ecommerceapp.payload.response.UserInfoResponse;
import com.ecommerce.ecommerceapp.service.AuthService;
import com.ecommerce.ecommerceapp.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//for Angular Client (withCredentials)
//@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthService authService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @LogExecutionTime
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @LogExecutionTime
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (authService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (authService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleService.findByName(ERole.ROLE_MODERATOR);
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleService.findByName(ERole.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        authService.saveUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PostMapping("/addRole")
    public ResponseEntity<?> addRole(@RequestBody Role role){
        roleService.saveRole(role);
        return ResponseEntity.ok(new MessageResponse("Role registered successfully!"));
    }
}
