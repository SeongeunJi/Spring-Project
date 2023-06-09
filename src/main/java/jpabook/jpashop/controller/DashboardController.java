package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/dashboard/dashboard.html";
    }
}
