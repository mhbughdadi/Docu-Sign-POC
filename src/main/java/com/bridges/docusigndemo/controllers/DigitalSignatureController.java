package com.bridges.docusigndemo.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1")
public class DigitalSignatureController {

    @Value("${docusign.clientId}")
    private String clientId;

    @Value("${docusign.redirectUri}")
    private String redirectUri;

    @GetMapping("/callback")
    public  String callback(@RequestParam("code") String code) {
        return "Callback received with code: " + code ;
    }

    @GetMapping("/auth")
    public void auth(HttpServletResponse response) throws IOException {
        String url = "https://account-d.docusign.com/oauth/auth?" +
                "response_type=code&scope=signature&client_id=" + clientId +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8);
        response.sendRedirect(url);
    }
}
