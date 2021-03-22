package com.example.adapters.database.course.acquired;

import com.example.adapters.database.course.acquired.model.AcquiredCourseModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcquiredCourseRepository extends CrudRepository<AcquiredCourseModel, String> {

    @Query(value = "SELECT * FROM TB_ACQUIRED_COURSE WHERE id_acquired_course = :idAcquiredCourse", nativeQuery = true)
    Optional<AcquiredCourseModel> findByIdAcquiredCourse(@Param(value = "idAcquiredCourse")String idAcquiredCourse);
}