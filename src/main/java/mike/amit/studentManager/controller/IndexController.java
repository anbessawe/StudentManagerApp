package mike.amit.studentManager.controller;

import mike.amit.studentManager.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    Student student;
    //AddStudent Mapping
    @GetMapping("addStudent")
    public String addStudent(){
        return "addStudent";
    }

    @PostMapping("add")
    public String addStudent(Student student, Model model){
        this.student = student;
        if(this.student != null)model.addAttribute("message", "Success");
        else model.addAttribute("message", "Failed");
        return "addStudent";
    }

    @GetMapping("updatedList")
    public String getUpdatedList(Model model){
        model.addAttribute(student);
        return "studentList";
    }

}
