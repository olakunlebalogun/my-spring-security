package com.olakunle.myspringsecurity.controllers;


import com.olakunle.myspringsecurity.entities.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("student/api")
public class StudentController {

    private static final List<Student> STUDENT  = Arrays.asList(
            new Student(1, "Lionel Messi"),
            new Student(2, "Neymar JR"),
            new Student(3, "Ansu Fati")
    );

    @GetMapping("/{id}")
    public Student getSingleStudent(@PathVariable("id") Integer studentId){
        return STUDENT.stream().filter(student -> Objects.equals(studentId, student.getStudentId()))
//        return STUDENT.stream().filter(student -> student.getStudentId() == studentId)
                .findFirst()
                .orElseThrow( () ->
                    new IllegalStateException(String.format("Student with %s not available", studentId))
                );
    }

}
