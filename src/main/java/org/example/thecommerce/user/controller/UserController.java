package org.example.thecommerce.user.controller;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.thecommerce.user.dto.UserDto;
import org.example.thecommerce.user.dto.UserListDto;
import org.example.thecommerce.user.dto.UserUpdateDto;
import org.example.thecommerce.user.entity.User;
import org.example.thecommerce.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiModelProperty
    @ApiOperation(value = "회원가입", notes = "입력된 정보를 기반으로 회원 정보를 데이터베이스에 저장합니다.")
    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody UserDto userDto){

        String resultMsg = userService.join(userDto);

        if (resultMsg.equals("회원가입 성공")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(resultMsg);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMsg);
        }
    }
    @ApiModelProperty
    @ApiOperation(value = "회원 목록 조회", notes = "입력된 회원들의 정보를 목록으로 조회합니다")
    @GetMapping("/list")
    public ResponseEntity<Page<UserListDto>> listUser(
            @RequestParam @ApiParam(value = "페이지 번호", example = "1") int page,
            @RequestParam @ApiParam(value = "한 페이지에 표시될 수 있는 최대 회원 수", example = "5") int pageSize,
            @RequestParam @ApiParam(value = "정렬방식 (enrollDate 또는 id)", example = "enrollDate") String sort
    ) {
        return ResponseEntity.ok(userService.getUserList(page, pageSize, sort));
    }

    @ApiModelProperty
    @ApiOperation(value = "회원 정보 수정", notes = "해당하는 아이디를 가진 회원의 정보가 수정됩니다.")
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody UserUpdateDto userUpdateDto) {
        try {
            User updatedUser = userService.updateUser(userId, userUpdateDto);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
