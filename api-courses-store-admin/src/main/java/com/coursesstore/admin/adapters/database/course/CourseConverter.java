package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.teacher.model.TeacherConverter;
import com.coursesstore.admin.core.domain.course.Course;

import java.util.UUID;

public class CourseConverter {

    private CourseConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static CourseModel toModel(String idCourse){
        var courseModel = new CourseModel();
        courseModel.setIdCourse(idCourse);

        return courseModel;
    }

    public static CourseModel toModel(Course course){
        var courseModel = new CourseModel();
        courseModel.setIdCourse(String.valueOf(course.getIdCourse()))   ;
        courseModel.setName(course.getName());
        courseModel.setOriginalValue(course.getOriginalValue());
        courseModel.setTeacherResponsible(TeacherConverter.toModel(course.getTeacherResponsible()));

        return courseModel;
    }

    public static Course toEntity(CourseModel courseModel){
        var course = new Course();
        course.setIdCourse(UUID.fromString(courseModel.getIdCourse()));
        course.setName(courseModel.getName());
        course.setOriginalValue(courseModel.getOriginalValue());
        course.setTeacherResponsible(TeacherConverter.toEntity(courseModel.getTeacherResponsible()));

        return course;
    }

}
