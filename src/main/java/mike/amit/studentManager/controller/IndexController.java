package mike.amit.studentManager.controller;

import mike.amit.studentManager.entity.Student;
import mike.amit.studentManager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class IndexController {

    private final StudentRepository studentRepository;

    @Autowired
    public IndexController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    //AddStudent Mapping
    @GetMapping("addStudent")
    public String showAddStudentPage(Student student){
        return "addStudent";
    }

    @PostMapping("addStudent")
    public String addStudentToList(@Valid Student student, BindingResult result, Model model){

        if(result.hasErrors())model.addAttribute("message", "Failed");
        else {
            studentRepository.save(student);
            model.addAttribute("message", "Success");
        }
        return "addStudent";
    }

    @GetMapping("updatedList")
    public String getUpdatedList(Model model){
        model.addAttribute("studentList",studentRepository.findAll());
        return "studentList";
    }

}
