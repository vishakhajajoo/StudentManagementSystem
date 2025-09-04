package com.vishi.StudentsManagementSystem.Controller;
import com.vishi.StudentsManagementSystem.controller.Controller;
import com.vishi.StudentsManagementSystem.entity.Student;
import com.vishi.StudentsManagementSystem.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class ControllerTest {

        @InjectMocks
        private Controller controller;

        @Mock
        private StudentService service;

        @Mock
        private Model model;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testGetAllStudents() {
            List<Student> mockStudents = Arrays.asList(
                    new Student(1, "Monika", "Geller", "monika@friends.com"),
                    new Student(2, "Chandler", "Bing", "chandler@friends.com")
            );

            when(service.getAllStudents()).thenReturn(mockStudents);

            String viewName = controller.getAllStudents(model);

            verify(service).getAllStudents();
            verify(model).addAttribute("students", mockStudents);
            assertEquals("students", viewName);
        }

        @Test
        void testCreateStudentForm() {
            String viewName = controller.createStudentForm(model);

            ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
            verify(model).addAttribute(eq("student"), captor.capture());

            Student student = captor.getValue();
            assertEquals(0, student.getId());
            assertEquals("create-student", viewName);
        }

        @Test
        void testSaveStudent() {
            Student student = new Student(1, "monika", "Geller", "monika@friends.com");
            String viewName = controller.saveStudent(student);

            verify(service).saveStudent(student);
            assertEquals("redirect:/students", viewName);
        }

        @Test
        void testUpdateStudentForm() {
            int studentId = 1;
            Student student = new Student(studentId, "Chandler", "Bing", "chandler@friends.com");

            when(service.getById(studentId)).thenReturn(student);

            String viewName = controller.updateStudentForm(studentId, model);

            verify(service).getById(studentId);
            verify(model).addAttribute("student", student);
            assertEquals("update_student", viewName);
        }

        @Test
        void testUpdateStudent() {
            int studentId = 1;
            Student existingStudent = new Student(studentId, "OldFirstName", "OldLastName", "old@example.com");
            Student updatedStudent = new Student(studentId, "NewFirstName", "NewLastName", "new@example.com");

            when(service.getById(studentId)).thenReturn(existingStudent);

            String viewName = controller.updateStudent(studentId, updatedStudent);

            verify(service).getById(studentId);
            verify(service).saveStudent(existingStudent);

            assertEquals("NewFirstName", existingStudent.getFirstName());
            assertEquals("NewLastName", existingStudent.getLastName());
            assertEquals("new@example.com", existingStudent.getEmail());

            assertEquals("redirect:/students", viewName);
        }

        @Test
        void testDeleteById() {
            int studentId = 1;

            String viewName = controller.deleteById(studentId);

            verify(service).deleteById(studentId);
            assertEquals("redirect:/students", viewName);
        }
    }



