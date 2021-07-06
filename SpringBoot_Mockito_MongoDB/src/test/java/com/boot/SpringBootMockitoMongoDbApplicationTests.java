package com.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.boot.controller.StudentController;
import com.boot.model.Student;
import com.boot.repository.StudentRepository;

@SpringBootTest
class SpringBootMockitoMongoDbApplicationTests {

	@Autowired
	StudentController controller;

	@MockBean
	StudentRepository repository;

	@Test
	void contextLoads() {
		Student stud1 = new Student(1, "Harini", "S", 415);
		Student stud2 = new Student(2, "Tamil", "A", 485);
		List<Student> StdList = Arrays.asList(stud1, stud2);

		insertCheck(stud1, stud2);

		fetchCheck(StdList);

		deleteCheck(stud1, stud2);
	}

	void insertCheck(Student stud1, Student stud2) {
		
		when(repository.save(stud1)).thenReturn(stud1);
		assertEquals("Added the Student : " + stud1.toString(), controller.insert(stud1));
		System.out.println("Insert Check Completed.....");

		when(repository.saveAll(Arrays.asList(stud1, stud2))).thenReturn(Arrays.asList(stud1, stud2));
		assertEquals("Added all Students", controller.insertAll(Arrays.asList(stud1, stud2)));
		System.out.println("Insert All Check Completed.....");
	}

	void fetchCheck(List<Student> StdList) {

		when(repository.findById(StdList.get(0).getStdId())).thenReturn(Optional.of(StdList.get(0)));
		Student fetchedStud = controller.getStudent(StdList.get(0).getStdId());
		assertEquals(StdList.get(0).toString(), fetchedStud.toString());
		System.out.println("Fetch Check Completed.....");

		when(repository.findAll()).thenReturn(StdList);
		List<Student> fetchedAllStud = controller.getAllStudent();
		for (int i = 0; i < fetchedAllStud.size(); i++)
			assertEquals(StdList.get(i).toString(), fetchedAllStud.get(i).toString());
		System.out.println("Fetch All Check Completed.....");

		List<Student> topperStd = controller.getTopper();
		for (int i = 0; i < topperStd.size(); i++)
			assertEquals(StdList.get(0).toString(), topperStd.get(i).toString());
		System.out.println("Fetch Many based on one Condition Check Completed.....");

		List<Student> highMarkStd = controller.getHighestMark(430);
		for (int i = 0; i < highMarkStd.size(); i++)
			assertEquals(StdList.get(1).toString(), highMarkStd.get(i).toString());
		System.out.println("Fetch Many based on many Condition Check Completed.....");
	}

	void deleteCheck(Student stud1, Student stud2) {

		doNothing().when(repository).deleteById(stud1.getStdId());
		assertEquals("Student Record deleted with id : " + stud1.getStdId(),
				controller.deleteStudent(stud1.getStdId()));
		System.out.println("Delete Check Completed.....");

		assertEquals("Student Record deleted with id",
				controller.deleteManyStudent(Arrays.asList(stud1.getStdId(), stud2.getStdId())));
		System.out.println("Delete Many Check Completed.....");

		doNothing().when(repository).deleteAll();
		assertEquals("All Records Deleted", controller.deleteAllStudent());
		System.out.println("Delete All Check Completed.....");
	}
}
