package com.coursesstore.admin.adapters.http.customer.post.dto;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PostCustomerConverter {

    private PostCustomerConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Customer toDomain (RequestPostCustomer body){
        var customer = new Customer();
        customer.setFirstname(body.getFirstname());
        customer.setLastname(body.getLastname());
        customer.setPhone(body.getPhone());
        customer.setLinkedIn(body.getLinkedIn());
        customer.setEmail(body.getEmail());
        customer.setCompany(body.getCompany());
        customer.setPosition(body.getPosition());

        return customer;
    }

    public static AcquiredCourse toDomainAcquiredCourse(RequestPostAcquiredCourseByCustomer body){
        var acquiredCourse = new AcquiredCourse();

        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        acquiredCourse.setAcquisitionDate(LocalDate.parse(body.getAcquisitionDate(),formatter));

        acquiredCourse.setValuePaid(NumberUtils.parseNumber(body.getValuePaid(),BigDecimal.class));

        var course = new Course();
        course.setIdCourse(UUID.fromString(body.getIdCourse()));
        acquiredCourse.setCourse(course);

        return acquiredCourse;
    }



    public static DesiredCourse toDomainDesiredCourse (RequestPostDesiredCourseByCustomer body){
        var desiredCourse = new DesiredCourse();

        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        desiredCourse.setDesireDate(LocalDate.parse(body.getDesireDate(),formatter));
        desiredCourse.setDesireDescription(body.getDesireDescription());

        var course = new Course();
        course.setIdCourse(UUID.fromString(body.getIdCourse()));
        desiredCourse.setCourse(course);

        return desiredCourse;
    }

}
