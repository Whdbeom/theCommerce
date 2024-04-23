package org.example.thecommerce.test;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class test {

    @ApiOperation(value = "value 테스트", notes = "note 테스트")
    @GetMapping("test1")
    public void tt(){
        System.out.println("겟매핑호출했다");
    }
}
