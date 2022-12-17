package com.example.demo.controller;

import com.example.demo.entity.Sleep;
import com.example.demo.entity.User;
import com.example.demo.repository.SleepRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sleep")
public class SleepController {
    private final SleepRepository sleepRepository;
    private final UserService userService;

    @Autowired
    public SleepController(SleepRepository sleepRepository, UserService userService) {
        this.sleepRepository = sleepRepository;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String showSleepList(Model model) {
        model.addAttribute("sleeps", sleepRepository.findByUser(userService.findByEmail(userService.getAuthentication().getName())));
        return "index-sleep";
    }

    @GetMapping("/new")
    public String showAddSleepForm(Sleep sleep) {
        return "add-sleep";
    }

    @PostMapping("/addsleep")
    public String addSleep(Sleep sleep, BindingResult result, Model model) {
        sleep.setUser(userService.findByEmail(userService.getAuthentication().getName()));
        sleepRepository.save(sleep);
        return "redirect:/sleep/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Sleep sleep = sleepRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid sleep Id:" + id));
        model.addAttribute("sleep", sleep);

        return "update-sleep";
    }

    @PostMapping("/update/{id}")
    public String updateSleep(@PathVariable("id") long id, Sleep sleep, BindingResult result, Model model) {
        sleep.setUser(userService.findByEmail(userService.getAuthentication().getName()));
        sleepRepository.save(sleep);

        return "redirect:/sleep/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteSleep(@PathVariable("id") long id, Model model) {
        Sleep sleep = sleepRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid sleep Id:" + id));
        sleepRepository.delete(sleep);

        return "redirect:/sleep/index";
    }
}
