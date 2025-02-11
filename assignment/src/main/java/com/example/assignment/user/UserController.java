package com.example.assignment.user;

import com.example.assignment.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원 가입", description = "회원가입을 하는 기능입니다")
    @PostMapping("/signup")
    public void signup(@RequestBody UserDto.SignupRequest dto) {
        userService.signup(dto);
    }

//    @GetMapping("{idx}")
//    public ResponseEntity<UserDto.UserInfoResponse> getUser(@PathVariable int idx) {
//
//    }

}
