package com.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.dao.StudentDao;
import com.spring.entity.Student;

public class App {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println("Spring ORM Program Started...");
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDao dao = context.getBean("studentDao", StudentDao.class);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean f = true;
		while (f) {
			System.out.println("PRESS 1 for Insert a Student");
			System.out.println("PRESS 2 for Display All Students data");
			System.out.println("PRESS 3 for Get Detail of Single Student");
			System.out.println("PRESS 4 for Delete Students Data");
			System.out.println("PRESS 5 for Update Student data");
			System.out.println("PRESS 6 for Exit");
			int input = Integer.parseInt(br.readLine());
			if (input == 1) {
				System.out.println("Add a student Data");
				System.out.println("Enter Student Id : ");
				int sId = Integer.parseInt(br.readLine());

				System.out.println("Enter Student Name :");
				String sName = br.readLine();

				System.out.println("Enter Student City: ");
				String sCity = br.readLine();

				Student s = new Student();
				s.setStudentId(sId);
				s.setStudentName(sName);
				s.setStudentCity(sCity);
				int r = dao.insert(s);
				System.out.println(r + " Student data Added Successfully");
				System.out.println("***************************************");
				System.out.println();
			} else if (input == 2) {
				System.out.println("Display all student Data");
				List<Student> allStudents = dao.getAllStudents();
				for (Student st : allStudents) {
					System.out.println("Id : " + st.getStudentId());
					System.out.println("Name : " + st.getStudentName());
					System.out.println("City : " + st.getStudentCity());
					System.out.println("___________________________________");
				}
			} else if (input == 3) {
				System.out.println("Display a single student Data");
				System.out.println("Enter user id : ");
				int userId = Integer.parseInt(br.readLine());
				Student student = dao.getStudent(userId);
				System.out.println("Id : " + student.getStudentId());
				System.out.println("Name : " + student.getStudentName());
				System.out.println("City : " + student.getStudentCity());
				System.out.println("___________________________________");
			} else if (input == 4) {
				System.out.println("Delete a student Data");
				System.out.println("Enter user id : ");
				int id = Integer.parseInt(br.readLine());
				dao.deleteStudent(id);
				System.out.println("Student deleted...");
			} else if (input == 5) {
				System.out.println("Update a student Data");
				System.out.println("Enter Student Id : ");
				int sId = Integer.parseInt(br.readLine());

				System.out.println("Enter Student Name :");
				String sName = br.readLine();

				System.out.println("Enter Student City: ");
				String sCity = br.readLine();

				Student s = dao.getStudent(102);
//				s.setStudentId(sId);
				s.setStudentName(sName);
				s.setStudentCity(sCity);
				dao.updateStudent(s);
				System.out.println("Student data Updated Successfully");
				System.out.println("***************************************");
				System.out.println();
				
			} else if (input == 6) {
				f = false;
			} else {
				break;
			}
		}
		System.out.println("Thankyou for using my application");
		System.out.println("See you soon!!");
	}
}
