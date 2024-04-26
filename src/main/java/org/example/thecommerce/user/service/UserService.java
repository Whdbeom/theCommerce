package org.example.thecommerce.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.thecommerce.user.dto.UserDto;
import org.example.thecommerce.user.entity.User;
import org.example.thecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Transactional
    public String join(UserDto userDto){

        String checkValidMsg = checkUserValid(userDto);

        if(!checkValidMsg.equals("ok")){
            return checkValidMsg;
        }

        User user = convertDtoToEntity(userDto);

        try {
            log.info("서비스 user : {} ", userDto);
            userRepository.save(user);
            return "회원가입 성공";
        } catch (Exception e) {
            log.error("회원가입 실패", e);
            return e.getMessage();
        }

    }
    private User convertDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPwd(userDto.getPwd());
        user.setNick(userDto.getNick());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        return user;
    }

    private String checkUserValid(UserDto userDto){
        Optional<User> existingUser = userRepository.findById(userDto.getId());
        if (existingUser.isPresent()) {
            return "이미 사용중인 아이디입니다.";
        }

        if (!userDto.getId().matches("^[a-zA-Z0-9]*$")) {
            return "아이디에는 영문자와 숫자만 사용할 수 있습니다.";
        }
        return "ok";
    }


}
