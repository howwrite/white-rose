package com.github.howwrite.whiterose.starter;

import cn.github.howwrite.whiterose.action.TwoWheel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱森林
 * @date 2020/12/27 11:07 下午
 */
@RestController
public class TestController {
    private final TwoWheel twoWheel = new TwoWheel(29, 28, 27, 25, 24, 23);

    @GetMapping("/test")
    public boolean action(@RequestParam String command) {
        if ("advance".equalsIgnoreCase(command)) {
            return twoWheel.advance();
        } else if ("stop".equalsIgnoreCase(command)) {
            return twoWheel.stop();
        } else if ("shutdown".equalsIgnoreCase(command)) {
            return twoWheel.shutdown();
        } else if ("back".equalsIgnoreCase(command)) {
            return twoWheel.back();
        } else if ("left".equalsIgnoreCase(command)) {
            return twoWheel.turnLeft();
        } else if ("right".equalsIgnoreCase(command)) {
            return twoWheel.turnRight();
        }
        return false;
    }
}
