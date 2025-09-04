package com.vishi.StudentsManagementSystem.Impl;

import com.vishi.StudentsManagementSystem.entity.Student;
import com.vishi.StudentsManagementSystem.repository.StudentRepository;
import com.vishi.StudentsManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    /**
     * Retrieves all students from the database.
     * <p>
     * Delegates to the {@code studentRepository.findAll()} method to fetch a list of all
     * {@link Student} entities.
     *
     * @return a list of all students in the database
     */
    @Override
    public List<Student> getAllStudents() {
        List<Student> list= studentRepository.findAll();
        return list;
    }

    /**
     * Saves a student entity to the database.
     * <p>
     * If the student already exists (based on its ID), this method updates the existing record.
     * Otherwise, it creates a new student record.
     *
     * @param student the {@link Student} entity to save
     * @return the saved {@link Student} entity, potentially updated with generated ID or other fields
     */
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }


    /**
     * Retrieves a student entity by its unique ID.
     *
     * @param id the unique identifier of the student
     * @return the {@link Student} entity with the given ID
     * @throws java.util.NoSuchElementException if no student is found with the specified ID
     */
    @Override
    public Student getById(int id) {
        return studentRepository.findById(id).get();
    }

    /**
     * Deletes a student entity from the repository based on the given ID.
     *
     * @param id the unique identifier of the student to be deleted
     */
    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }
}
