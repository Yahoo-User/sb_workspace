package org.zerock.myapp.security;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Log4j2
@NoArgsConstructor

@Controller
public class SecurityController {


    @GetMapping("/system/accessDenied")
    void accessDenied() {}   // View-Controller

    @GetMapping("/system/login")
    void login() {}         // View-Controller

    @GetMapping("/system/logout")
    void logout() {}        // View-Controller

    @GetMapping("/admin/adminPage")
    void adminPage() {}     // View-Controller

} // end class
