package com.proj.ecom.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    @GetMapping("/session")
    public String getSessionId(HttpSession session) {
        return session.getId();
    }

    @GetMapping("/setSessionAttribute")
    public String setSessionAttribute(HttpSession session) {
        session.setAttribute("attributeName", "attributeValue");
        return "Attribute set in session";
    }

    @GetMapping("/getSessionAttribute")
    public String getSessionAttribute(HttpSession session) {
        return (String) session.getAttribute("attributeName");
    }
}
