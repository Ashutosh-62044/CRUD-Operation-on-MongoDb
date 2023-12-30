package com.MongoDBProject.repository;

import com.MongoDBProject.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, Long> {
}
