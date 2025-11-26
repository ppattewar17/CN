package com.example.webpage_student.controller;

import com.example.webpage_student.model.StudentResult;
import com.example.webpage_student.repository.StudentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResultController {

    @Autowired
    private StudentResultRepository repo;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("studentResult", new StudentResult());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@ModelAttribute StudentResult result, Model model) {
        int s1 = result.getSub1Insem() + result.getSub1Endsem();
        int s2 = result.getSub2Insem() + result.getSub2Endsem();
        int s3 = result.getSub3Insem() + result.getSub3Endsem();
        int s4 = result.getSub4Insem() + result.getSub4Endsem();

        int total = s1 + s2 + s3 + s4;
        double percent = total / 4.0;
        double cgpa = percent / 9.5;

        result.setTotalMarks(total);
        result.setPercentage(Math.round(percent * 100.0) / 100.0);
        result.setCgpa(Math.round(cgpa * 100.0) / 100.0);

        repo.save(result);

        model.addAttribute("result", result);
        return "certificate";
    }
}
