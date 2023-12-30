package com.MongoDBProject.controller;

import com.MongoDBProject.entity.Student;
import com.MongoDBProject.payload.StudentDto;
import com.MongoDBProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        Student saveStudent = studentRepository.save(student);
        return new ResponseEntity<>(saveStudent, HttpStatus.CREATED);
    }

    @GetMapping("/showStudent")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> allStudent = studentRepository.findAll();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);

    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable long studentId) {
        studentRepository.deleteById(studentId);
        return new ResponseEntity<>("delete Student !!", HttpStatus.OK);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable long studentId, @RequestBody StudentDto studentDto) {

        Optional<Student> studentFind = studentRepository.findById(studentId);
        if (studentFind.isPresent()) {
            Student student = studentFind.get();


            student.setRollNo(studentDto.getRollNo());
            student.setName(studentDto.getName());
            student.setCity(studentDto.getCity());
            student.setPhoneNo(student.getPhoneNo());

            Student saveStudent = studentRepository.save(student);

            StudentDto dto = new StudentDto();
            dto.setRollNo(saveStudent.getRollNo());
            dto.setName(saveStudent.getName());
            dto.setPhoneNo(saveStudent.getPhoneNo());
            dto.setCity(saveStudent.getCity());

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            // If the student with the given ID is not found, return 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}