package io.github.alirezamht.educational.service;

import io.github.alirezamht.educational.model.Course;
import io.github.alirezamht.educational.repository.CourseRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("courseService")
@Transactional
public class CourseServiceIm implements CourseService {

    private CourseRepository courseRepository;

    public CourseServiceIm(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course getCourseById(Long id)  {
        return courseRepository.getCoursesByiD(id);
    }

    @Override
    public void update(Course course) {

        courseRepository.update(course.getCapacity() , course.getiD());
    }
}
