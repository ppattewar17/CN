package demo.demo;
import demo.demo.StudentResult;
import demo.demo.StudentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResultController {

    @Autowired
    private StudentResultRepository repository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("studentResult", new StudentResult());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculateAndStore(@ModelAttribute StudentResult studentResult, Model model) {
        int s1 = studentResult.getSub1Insem() + studentResult.getSub1Endsem();
        int s2 = studentResult.getSub2Insem() + studentResult.getSub2Endsem();
        int s3 = studentResult.getSub3Insem() + studentResult.getSub3Endsem();
        int s4 = studentResult.getSub4Insem() + studentResult.getSub4Endsem();
        int s5 = studentResult.getSub5Insem() + studentResult.getSub5Endsem();

        int totalMarks = s1 + s2 + s3 + s4 + s5;
        double percentage = (totalMarks / 5.0);
        double cgpa = percentage / 9.5;

        studentResult.setTotalMarks(totalMarks);
        studentResult.setPercentage(Math.round(percentage * 100.0) / 100.0);
        studentResult.setCgpa(Math.round(cgpa * 100.0) / 100.0);

        StudentResult saved = repository.save(studentResult);
        model.addAttribute("result", saved);

        return "certificate";
    }
}

