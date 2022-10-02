package com.olakunle.myspringsecurity.controllers;

import com.olakunle.myspringsecurity.entities.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/student/api")
public class StudentManagementController {
    private static final List<Student> STUDENT  = Arrays.asList(
            new Student(1, "Lionel Messi"),
            new Student(2, "Neymar JR"),
            new Student(3, "Ansu Fati")
    );

    @GetMapping("/all")
    public List<Student> getAllStudent(){
        return STUDENT;
    }

    @PostMapping("/add")
    public void registerStudent(@RequestBody Student student){
        System.out.println("Registering Student" + student) ;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Integer studentId){
        System.out.println("Deleting Student" + studentId);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable("id") Integer studentId, @RequestBody Student student){
        System.out.println("Updating Student" + studentId + " with data " + student);
    }
}
