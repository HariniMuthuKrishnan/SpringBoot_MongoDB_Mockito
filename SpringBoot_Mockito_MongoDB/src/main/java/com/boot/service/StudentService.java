package com.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.boot.model.Student;
import com.boot.repository.StudentRepository;

@Component
public class StudentService {

	@Autowired
	StudentRepository repository;

	public Student insert(Student std) {
		System.out.println("Record Added" + std.toString());
		Student insertedStd = repository.save(std);
		return insertedStd;
	}

	public List<Student> insertAll(List<Student> std) {
		System.out.println("Records Added" + std.toString());
		List<Student> insertedStds = repository.saveAll(std);
		return insertedStds;
	}

	public List<Student> getAll() {
		System.out.println("All Record Searched");
		return repository.findAll();
	}

	public Student getById(@PathVariable int id) {
		System.out.println("Record Searched");
		Optional<Student> s = repository.findById(id);
		Student dd = s.isPresent() ? s.get() : new Student();
		return dd;
	}

	public int delete(@PathVariable int id) {
		System.out.println("Record Deleted");
		repository.deleteById(id);
		return id;
	}

	public void deleteMany(List<Integer> ids) {
		for (Integer id : ids)
			repository.deleteById(id);
	}

	public void deleteAll() {
		repository.deleteAll();

	}

	public List<Student> getHighestMark(int mark) // Get when Total > mark
	{
		List<Student> stds = getAll();
		List<Student> finalList = new ArrayList<Student>();
		for (Student std : stds) {
			if (std.getStdTotal() > mark)
				finalList.add(std);
		}
		return finalList;
	}

	public List<Student> getTopper() {
		List<Student> stds = getAll();
		List<Student> finalList = new ArrayList<Student>();
		for (Student std : stds) {
			if (std.getStdGrade() == "S")
				finalList.add(std);
		}
		return finalList;
	}
}
