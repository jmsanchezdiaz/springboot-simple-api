package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path="{studentID}")
    public Student getStudent(@PathVariable("studentID") Long id){
        return this.studentService.getStudent(id);
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        this.studentService.addStudent(student);
    }

    @DeleteMapping(path="{studentID}")
    public void removeStudent(@PathVariable("studentID") Long id){
        this.studentService.deleteStudent(id);
    }

    @PutMapping(path="{studentID}")
    public void updateStudent(@PathVariable("studentID") Long id,
                             @RequestBody Student student){
        this.studentService.updateStudent(id, student.getEmail(), student.getName());
    }
}
