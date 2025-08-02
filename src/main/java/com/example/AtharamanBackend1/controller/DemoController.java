package com.example.AtharamanBackend1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }


    @GetMapping("/guide_only")
    public ResponseEntity<String> guideOnly() {
        return ResponseEntity.ok("Hello from guide only url");
    }

    @GetMapping("/so")
    public ResponseEntity<String> soOnly() {
        return ResponseEntity.ok("Hello from guide only url");
    }
}

