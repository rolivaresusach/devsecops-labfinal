package org.soyphea.k8s.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soyphea.k8s.config.UserConfig;
import org.soyphea.k8s.domain.User;
import org.soyphea.k8s.srevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Request;

import java.util.List;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private UserService userService;

    private String noInjected = "NonInjected";

    public Controller() {
        log.info("Initialised Bean.");
    }

    @GetMapping("/k8s/{name}")
    public String k8sGreeting(@PathVariable("name") String name) throws Exception {
        log.info("Got the request with name:{}", name);
        if (name != null)
            this.noInjected = name;
        return String.format("Hi %s- I am ConfigMap running in side k8s with value %s", this.noInjected, userConfig);
    }

    @GetMapping("/users/{contain_name}")
    public List<User> getUsersByContainName(@PathVariable("contain_name") String containName) {
        return userService.getUser(containName);
    }
}
