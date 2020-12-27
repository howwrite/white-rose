package com.github.howwrite.whiterose.starter;

import cn.github.howwrite.whiterose.action.FourWheel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱森林
 * @date 2020/12/27 11:07 下午
 */
@RestController
public class TestController {
    private final FourWheel fourWheel = new FourWheel(29, 25, 28, 27, 24, 23);

    @GetMapping("/test")
    public boolean action(@RequestParam String command) {
        if ("advance".equalsIgnoreCase(command)) {
            return fourWheel.advance();
        } else if ("stop".equalsIgnoreCase(command)) {
            return fourWheel.stop();
        } else if ("shutdown".equalsIgnoreCase(command)) {
            return fourWheel.shutdown();
        }
        return false;
    }
}
