package com.vishi.StudentsManagementSystem.controller;

import com.vishi.StudentsManagementSystem.entity.Student;
import com.vishi.StudentsManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private StudentService service;

    /**
     * Handles HTTP GET requests for the "/students" URL.
     * <p>
     * Retrieves a list of all students from the service layer and adds it to the model
     * under the attribute name "students". This data is then used by the "students.html"
     * Thymeleaf view to render the student list.
     *
     * @param model the Model object used to pass data from the controller to the view
     * @return the name of the view template ("students") to be rendered
     */
    @GetMapping("/students")
    public String getAllStudents(Model model){
        model.addAttribute("students", service.getAllStudents());
        return "students"; //view
    }

    /**
     * Handles HTTP GET requests for displaying the update form of a specific student.
     * <p>
     * Retrieves the student with the specified ID from the service layer, adds it to the model
     * under the attribute name "student", and returns the name of the Thymeleaf template used
     * to render the update form.
     *
     * @paramid the ID of the student to be updated
     * @param model the Model object used to pass the student data to the view
     * @return the name of the view template ("update_student") for rendering the update form
     */
    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        Student student = new Student();//hold
        model.addAttribute("student", student);
        return "create-student";
    }

    /**
     * Handles HTTP POST requests for saving a new student.
     * <p>
     * Binds form data to a {@link Student} object using the @ModelAttribute annotation,
     * then delegates the saving logic to the service layer. After saving, it redirects
     * to the list of all students.
     *
     * @param student the Student object populated from the form submission
     * @return a redirect string to the "/students" URL after successful save
     */
    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute("student") Student student){
        service.saveStudent(student);
        return "redirect:/students";
    }

    /**
     * Handles HTTP GET requests to display the update form for a specific student.
     * <p>
     * Fetches the student with the given ID from the service layer and adds it to the model
     * with the attribute name "student". The returned view ("update_student") will use this
     * data to pre-fill the form fields for editing.
     *
     * @param id    the ID of the student to be updated
     * @param model the Model object used to pass the student data to the view
     * @return the name of the Thymeleaf template ("update_student") for rendering the update form
     */
    @GetMapping("/students/update/{id}")
    public String updateStudentForm(@PathVariable int id,Model model){
        model.addAttribute("student",service.getById(id));
        return "update_student";
    }

    /**
     * Handles HTTP POST requests to update an existing student.
     * <p>
     * Retrieves the existing student from the database using the provided ID,
     * then updates its fields with the data submitted via the form. The updated
     * student is then saved via the service layer. After saving, the method
     * redirects to the list of all students.
     *
     * @param id      the ID of the student to update
     * @param student a Student object populated with updated data from the form
     * @return a redirect string to the "/students" URL after successful update
     */
   @PostMapping("/students/update/{id}")
   public String updateStudent(@PathVariable int id, @ModelAttribute("student") Student student){
        Student existingStudent =service.getById(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        service.saveStudent(existingStudent);
        return "redirect:/students";
   }


    /**
     * Handles HTTP GET requests to delete a student by their ID.
     * <p>
     * Delegates the deletion to the service layer using the provided student ID.
     * After successful deletion, redirects to the list of all students.
     *
     * @param id the ID of the student to be deleted
     * @return a redirect string to the "/students" URL after deletion
     */
   @GetMapping("/students/delete/{id}")
   public String deleteById(@PathVariable int id){
        service.deleteById(id);
        return "redirect:/students";
   }
}
