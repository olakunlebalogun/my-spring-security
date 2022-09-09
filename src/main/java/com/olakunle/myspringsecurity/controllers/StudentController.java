package com.olakunle.myspringsecurity.controllers;


import com.olakunle.myspringsecurity.entities.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("security")
public class StudentController {

    private static final List<Student> STUDENT  = Arrays.asList(
            new Student(1, "Lionel Messi"),
            new Student(2, "Neymar JR"),
            new Student(3, "Ansu Fati")
    );

    @GetMapping("/{id}")
    public Student getSingleStudent(@PathVariable("id") Integer studentId){
        return STUDENT.stream().filter(student -> student.getStudentId() == studentId)
                .findFirst()
                .orElseThrow( () ->
                    new IllegalStateException("Student with " + studentId + " not available")
                );
    }

}
