package com.vishi.StudentsManagementSystem.Impl;
import com.vishi.StudentsManagementSystem.entity.Student;
import com.vishi.StudentsManagementSystem.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ServiceImplTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private ServiceImpl serviceImpl;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student(1, "Monika", "Geller", "monika.geller@friends.com");
    }

    @Test
    void testGetAllStudents() {
        List<Student> mockList = Arrays.asList(student);
        when(studentRepository.findAll()).thenReturn(mockList);

        List<Student> result = serviceImpl.getAllStudents();
        assertEquals(1, result.size());
        assertEquals("Monika", result.get(0).getFirstName());

        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testSaveStudent() {
        when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = serviceImpl.saveStudent(student);
        assertNotNull(savedStudent);
        assertEquals("monika.geller@friends.com", savedStudent.getEmail());

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetById() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        Student result = serviceImpl.getById(1);
        assertNotNull(result);
        assertEquals("Geller", result.getLastName());

        verify(studentRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteById() {
        doNothing().when(studentRepository).deleteById(1);

        serviceImpl.deleteById(1);
        verify(studentRepository, times(1)).deleteById(1);
    }
}
