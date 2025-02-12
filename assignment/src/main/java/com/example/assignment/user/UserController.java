package com.example.assignment.user;

import com.example.assignment.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "유저기능")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원 가입", description = "회원가입을 하는 기능입니다")
    @PostMapping("/signup")
    public void signup(@RequestBody UserDto.SignupRequest dto) {
        userService.signup(dto);
    }

    @Operation(summary = "유저 정보 검색", description = "한 유저의 세부정보를 검색하는 기능입니다.")
    @GetMapping("{idx}")
    public ResponseEntity<UserDto.UserInfoResponse> getUser(@PathVariable Long idx) {
        UserDto.UserInfoResponse dto = userService.getUserInfo(idx);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "구현중", description= "아직 구현중입니다")
    @GetMapping("/getinfo")
    public ResponseEntity<List<UserDto.UserInfoResponse>> getUsers() {
        List<UserDto.UserInfoResponse> dtoList = userService.getUserInfoList();
        return ResponseEntity.ok(dtoList);
    }

}
