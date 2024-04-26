package org.example.thecommerce.user.controller;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.thecommerce.user.dto.UserDto;
import org.example.thecommerce.user.entity.User;
import org.example.thecommerce.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService memberService;

    @ApiModelProperty
    @ApiOperation(value = "회원가입", notes = "입력된 정보를 기반으로 회원 정보를 데이터베이스에 저장합니다.")
    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody UserDto userDto) throws IOException {

        log.info("memberEntity : {} ", userDto);

        System.out.println("조인메서드 실행");
        String resultMsg = memberService.join(userDto);

        if (resultMsg.equals("회원가입 성공")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(resultMsg);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMsg);
        }
    }


}
