package com.example.adapters.database.course.desired;

import com.example.adapters.database.course.desired.model.DesiredCourseModel;
import com.example.core.domain.course.desired.DeleteDesiredCoursePort;
import com.example.core.domain.course.desired.DesiredCourse;
import org.springframework.stereotype.Component;

@Component
public class DeleteDesiredCourse implements DeleteDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;

    public DeleteDesiredCourse(DesiredCourseRepository desiredCourseRepository)
    { this.desiredCourseRepository = desiredCourseRepository; }

    @Override
    public void deleteDesiredCourse(DesiredCourse desiredCourse) {

        DesiredCourseModel desiredCourseToDelete = null;

        if (desiredCourse.getIdDesiredCourse() != null)
            desiredCourseToDelete = desiredCourseRepository.findByIdDesiredCourse(desiredCourse.getIdDesiredCourse().toString()).get();

        desiredCourseRepository.delete(desiredCourseToDelete);
    }
}
