package com.example.assignment.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {
    @Getter
    public static class SignupRequest{
        private String name; //이름
        private String nickName; //별명
        private String password; //비밀번호
        private Long phone; //전화번호
        private String email; //이메일
        private String sex; //성별

        public User toEntity(String encodedPassword){
            return  User.builder()
                    .name(name)
                    .nickName(nickName)
                    .password(encodedPassword)
                    .phone(phone)
                    .email(email)
                    .sex(sex)
                    .build();
        }

    }
    @Getter
    public static class loginRequest{
        private String email;
        private String password;

    }

    @Builder @Getter @AllArgsConstructor @NoArgsConstructor
    public static class UserInfoResponse{
        private String name; //이름
        private String nickName; //별명
        private Long phone; //전화번호
        private String email; //이메일
        private String sex; //성별

        public static UserInfoResponse from(User user){
            return UserInfoResponse.builder()
                    .name(user.getName())
                    .nickName(user.getNickName())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .sex(user.getSex())
                    .build();
        }


    }


}