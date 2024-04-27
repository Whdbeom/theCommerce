package org.example.thecommerce.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.thecommerce.user.dto.UserDto;
import org.example.thecommerce.user.dto.UserUpdateDto;
import org.example.thecommerce.user.entity.User;
import org.example.thecommerce.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String join(UserDto userDto) {

        String checkValidMsg = checkUserValid(userDto);

        if (!checkValidMsg.equals("ok")) {
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

    public Page<User> getUserList(int page, int pageSize, String sort) {

        // 페이지 번호와 페이지 크기가 유효한지 검사
        if (page <= 0 || pageSize <= 0) {
            throw new IllegalArgumentException("페이지 번호와 페이지 크기는 음수가 될 수 없습니다.");
        }
        // 정렬 방식 검사
        if (!isValidSort(sort)) {
            throw new IllegalArgumentException("id 또는 enrollDate를 입력해야합니다.");
        }

        Sort sortString = Sort.by(sort).descending();
        Pageable pageable = PageRequest.of(page-1, pageSize, sortString);
        return userRepository.findAll(pageable);
    }

    @Transactional
    public User updateUser(String userId, UserUpdateDto userUpdateDto) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("해당 유저를 찾을 수 없습니다."));

        if (userUpdateDto.getPwd() != null) user.setPwd(userUpdateDto.getPwd());
        if (userUpdateDto.getNick() != null) user.setNick(userUpdateDto.getNick());
        if (userUpdateDto.getPhone() != null) user.setPhone(userUpdateDto.getPhone());
        if (userUpdateDto.getEmail() != null) user.setEmail(userUpdateDto.getEmail());

        return userRepository.save(user);
    }

    private User convertDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPwd(passwordEncoder.encode(userDto.getPwd()));
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

    private boolean isValidSort(String sort) {
        return sort.equals("enrollDate") || sort.equals("id");
    }
}
