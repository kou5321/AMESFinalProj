package com.kou5321.jobPortalWebsite.user.controller;

import com.kou5321.jobPortalWebsite.user.dto.SignUpRequest;
import com.kou5321.jobPortalWebsite.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import com.kou5321.jobPortalWebsite.user.dto.LoginRequest;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/api/users")
    public ModelAndView signUp(@RequestBody SignUpRequest request, HttpServletRequest httpServletRequest) {
        userService.signUp(request);

        // Redirect to login API to automatically login when signup is complete
        LoginRequest loginRequest = new LoginRequest(request.email(), request.password());
        httpServletRequest.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return new ModelAndView("redirect:/api/users/login", "user", Map.of("user", loginRequest));
    }
}