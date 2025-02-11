package com.example.assignment.user;

import com.example.assignment.user.model.User;
import com.example.assignment.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(UserDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = userRepository.save(dto.toEntity(encodedPassword));

    }

    public UserDto.UserInfoResponse getUserInfo(Long idx) {
        User user = userRepository.findById(idx).orElseThrow();
        UserDto.UserInfoResponse dto = UserDto.UserInfoResponse.from(user);
        return dto;
    }

    public List<UserDto.UserInfoResponse> getUserInfoList(){
        List<User> userList = userRepository.findAll();
        List<UserDto.UserInfoResponse> dtoList = new ArrayList<>();

        for(User user : userList){
            UserDto.UserInfoResponse dto = UserDto.UserInfoResponse.from(user);
            dtoList.add(dto);
        }
        return dtoList;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByEmail(username);
        if (result.isPresent()) {
            User user = result.get();
            return user;
        }
        return null;
    }



}
