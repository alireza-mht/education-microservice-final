package io.github.alirezamht.educational.repository;

import io.github.alirezamht.educational.model.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CourseRepository extends CrudRepository<Course , Long>  {
    //Course findCourseById(Long s);

    //Optional<Course> findById(Long c);

  //   Course findCoursById(Long c);

     Course getCoursesByiD(Long c);

   // Course findById(Long c);

    @Modifying
    @Query("UPDATE Course c SET  c.capacity= :Capacity WHERE c.iD = :ID")
    void update(@Param("Capacity") Long Capacity , @Param("ID") Long ID);
}
