package mike.amit.studentManager.controller;

import mike.amit.studentManager.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class IndexController {
    Student student;
    //AddStudent Mapping
    @GetMapping("addStudent")
    public String showAddStudentPage(@ModelAttribute Student student, BindingResult result){
        this.student = student;
        return "addStudent";
    }

    @PostMapping("addStudent")
    public String addStudentToList(@ModelAttribute Student student, BindingResult result, Model model){
        this.student = student;
        if(result.hasErrors())model.addAttribute("message", "Failed");
        else model.addAttribute("message", "Success");
        return "addStudent";
    }

    @GetMapping("updatedList")
    public String getUpdatedList(Model model){
        model.addAttribute("student",this.student);
        return "studentList";
    }

}
