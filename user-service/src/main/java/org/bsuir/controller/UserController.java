package org.bsuir.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bsuir.dto.request.UserRequest;
import org.bsuir.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String createUser(@RequestBody UserRequest userRequestDto) {
        return userService.createUserGetUserId(userRequestDto);
    }


}
