package com.sendriods.demo.Controller;

import com.sendriods.demo.Dao.DivisionRepository;
import com.sendriods.demo.Dao.RoleRepository;
import com.sendriods.demo.Dao.UserRepository;
import com.sendriods.demo.Domain.ERole;
import com.sendriods.demo.Domain.Role;
import com.sendriods.demo.Domain.User;
import com.sendriods.demo.payload.request.LoginRequest;
import com.sendriods.demo.payload.request.SignupRequest;
import com.sendriods.demo.payload.response.JwtResponse;
import com.sendriods.demo.payload.response.MessageResponse;
import com.sendriods.demo.security.jwt.JwtUtils;
import com.sendriods.demo.security.services.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    final
    AuthenticationManager authenticationManager;

    final
    UserRepository userRepository;

    final
    DivisionRepository divisionRepository;

    final
    PasswordEncoder encoder;

    final
    JwtUtils jwtUtils;

    final RoleRepository roleRepository;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, DivisionRepository divisionRepository, PasswordEncoder encoder, JwtUtils jwtUtils, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.divisionRepository = divisionRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getAge(),
                userDetails.getUsername(),
                userDetails.getEmail()
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getAge(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        System.out.println(user.toString());
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
