package io.github.alirezamht.educational.service;

import io.github.alirezamht.educational.model.Student;

public interface StudentService {

    Student getStudentByStdNo(String StdNo);

    void save(Student student);
}
