package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return this.studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentByEmail = this.studentRepository.findStudentByEmail(student.getEmail());

        if(studentByEmail.isPresent()) throw new IllegalStateException("Un estudiante con ese email ya existe!");

        this.studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean existentStudent = this.studentRepository.existsById(id);
        if(!existentStudent) throw new IllegalStateException("No existe estudiante con ese id!");
        this.studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String email, String name) {
        Student existentStudent = this.studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No existe estudiante con ese id!"));

        if(name != null && name.length() > 0 && !name.equals(existentStudent.getName())) {
            existentStudent.setName(name);
        }

        if(email != null && email.length() > 0 && !email.equals(existentStudent.getEmail())) {
            Optional<Student> studentByEmail = this.studentRepository.findStudentByEmail(email);
            if(studentByEmail.isPresent()) throw new IllegalStateException("Un estudiante con ese email ya existe!");

            existentStudent.setEmail(email);
        }
    }

    public Student getStudent(Long id) {
        return this.studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Un estudiante con ese ID no existe!"));
    }
}
