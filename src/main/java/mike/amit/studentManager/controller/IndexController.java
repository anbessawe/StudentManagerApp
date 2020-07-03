package mike.amit.studentManager.controller;

import mike.amit.studentManager.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("student", new Student(1,
                "Michael",
                "Haile",
                "email"));
        return "index";
    }

}
