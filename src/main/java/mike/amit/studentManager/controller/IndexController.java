package mike.amit.studentManager.controller;

import mike.amit.studentManager.entity.Student;
import mike.amit.studentManager.repository.StudentRepository;
import mike.amit.studentManager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class IndexController {


    private final StudentService studentService;

    @Autowired
    public IndexController(StudentService studentService) {
        this.studentService = studentService;
    }

    //AddStudent Mapping
    @GetMapping("addStudent")
    public String showAddStudentPage(Student student, Model model) {
        model.addAttribute("message", null);
        return "addStudent";
    }

    @PostMapping("addStudent/{id}")
    public String addStudentToList(@PathVariable("id") long id, @Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Failed");
        } else {
            studentService.save(student);
            model.addAttribute("message", "Success");
            model.addAttribute("student", new Student());
        }

        return "addStudent";
    }

    @GetMapping("updatedList")
    public String getUpdatedList(Model model) {
        model.addAttribute("studentList", studentService.findAllStudents());
        return "studentList";
    }

    @GetMapping("updateStudent/{id}")
    public String updateStudent(@PathVariable long id, Model model) {
        Student student = studentService.findById(id).orElseThrow(() -> new IllegalArgumentException("Can not find Student Id:" + id));
        model.addAttribute("student", student);
        return "addStudent";
    }

    @GetMapping("deleteStudent/{id}")
    public String removeStudent(@PathVariable long id, Model model) {
        Student student = studentService.findById(id).orElseThrow(() -> new IllegalArgumentException("Can not find Student Id:" + id));
        studentService.delete(student);
        model.addAttribute("studentList", studentService.findAllStudents());
        return "studentList";
    }
}
