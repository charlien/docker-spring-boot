package com.webapp.foobar;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerEchoController {
    @GetMapping("/echo")
    @ResponseBody
    public String getMessage(@RequestParam("msg") Optional<String> message) {
        return message.orElse("no message sent!");
    }
}