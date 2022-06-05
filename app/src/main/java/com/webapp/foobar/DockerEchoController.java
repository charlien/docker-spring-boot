package com.webapp.foobar;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/echo")
public class DockerEchoController {
    /*
     * Endpoint for testing GET /echo with argument
     */
    @GetMapping
    @ResponseBody
    public String getMessage(@RequestParam("msg") Optional<String> message) {
        return message.orElse("no message sent!");
    }

    /*
     * Endpoint for testing GET /echo/message with url encoded message
     */
    @GetMapping("{message}")
    @ResponseBody
    public String getMessagePath(@PathVariable Optional<String> message) {
        return message.orElse("no message sent!");
    }

    /*
     * Endpoint for testing POST /echo with argument
     */
    @PostMapping
    @ResponseBody
    public String postMessage(@RequestParam("msg") Optional<String> message) {
        return message.orElse("no message sent!");
    }
}