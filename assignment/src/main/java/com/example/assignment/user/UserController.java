package com.example.assignment.user;

import com.example.assignment.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("{idx}")
    public ResponseEntity<UserDto.UserInfoResponse> getUser(@PathVariable Long idx) {
        UserDto.UserInfoResponse dto = userService.getUserInfo(idx);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getinfo")
    public ResponseEntity<List<UserDto.UserInfoResponse>> getUsers() {
        List<UserDto.UserInfoResponse> dtoList = userService.getUserInfoList();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/")
    public ResponseEntity

}
