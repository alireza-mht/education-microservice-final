package io.github.alirezamht.educational.service;

import io.github.alirezamht.educational.model.Course;

public interface CourseService {

    Course getCourseById(String id);

    void update(Course course);
}
