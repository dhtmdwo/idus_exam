package com.example.assignment.user.model;

import lombok.Getter;

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

}