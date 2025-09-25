package com.example.mainService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * <div><strong>Project name:</strong> DemoOAuth2 </div>
 * <div><strong>Creation date:</strong> 2025-09-21 </div>
 * </pre>
 *
 * @author Ivannikov Alexey
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String hello(
        @RequestHeader("X-name") String name
    ) {
        return "Hello " + name;
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}
