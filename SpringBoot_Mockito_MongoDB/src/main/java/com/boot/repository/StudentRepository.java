package com.boot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.boot.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {

}
