package ru.playground.playground.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    // Страница для GET-запроса на /login
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login";  // возвращает имя шаблона (например, login.html)
    }

    // Страница для успешной аутентификации
    @GetMapping("/login-success")
    public String loginSuccess() {
        return "redirect:/home";  // Перенаправление на домашнюю страницу после успешного входа
    }

    // Страница для выхода
    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "redirect:/login";  // Перенаправление на страницу логина после выхода
    }
}
