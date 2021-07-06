package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Student;
import com.boot.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService service;

	/*
	 * Insert 1 Record param Student object
	 */
	@PostMapping("/insertStudent")
	public String insert(@RequestBody Student std) {
		return "Added the Student : " + service.insert(std);
	}

	/*
	 * Insert Multiple Records param list of Student objects
	 */
	@PostMapping("/insertAllStudent")
	public String insertAll(@RequestBody List<Student> std) {
		 service.insertAll(std);
		return "Added all Students";
	}

	/*
	 * Search 1 Record param Student Id
	 */
	@GetMapping("/findStudent/{id}")
	public Student getStudent(@PathVariable int id) {
		return service.getById(id);
	}

	/*
	 * Search All Records
	 * 
	 */
	@GetMapping("/findAllStudent")
	public List<Student> getAllStudent() {
		return service.getAll();
	}

	/*
	 * Delete 1 Record param Student Id
	 */
	@DeleteMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable int id) {
		return "Student Record deleted with id : " + service.delete(id);
	}

	/*
	 * Delete Muliple Records param List of Student Ids
	 */
	@DeleteMapping("/deleteManyStudent")
	public String deleteManyStudent(@RequestBody List<Integer> ids) {
		service.deleteMany(ids);
		return "Student Record deleted with id";
	}

	/*
	 * Delete All Records
	 * 
	 */
	@DeleteMapping("/deleteAllStudent")
	public String deleteAllStudent() {
		service.deleteAll();
		return "All Records Deleted";
	}

	/*
	 * Get the list of records with higher than giver total mark param Student Mark
	 */
	@GetMapping("/findToppers/{mark}")
	public List<Student> getHighestMark(@PathVariable int mark) {
		return service.getHighestMark(mark);
	}

	/*
	 * Get the list of records with highest Grade("S")
	 * 
	 */
	@GetMapping("/findToppers")
	public List<Student> getTopper() {
		return service.getTopper();
	}

}
