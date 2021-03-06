package com.coursesstore.admin.adapters.http.customer.delete;


import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.coursesstore.admin.adapters.AdapterUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8109"})
@AutoConfigureMockMvc
class DeleteCustomerControllerTest {

    final String REQUEST_PATH = "/courses-store/customer";

    Customer customerCreatedToAcquireAndDesire;
    Teacher teacher;

    @BeforeEach
    public void setUp(){
        customerCreatedToAcquireAndDesire=registerANewCustomer();
        teacher = registerANewTeacher();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to delete a Customer, When its requested to delete a Customer, Then response with the status OK")
    void Given_a_valid_Request_to_delete_a_Customer_When_its_requested_to_delete_a_Customer_Then_response_with_the_status_OK() throws Exception {

        ///Arrange
        Customer customerCreated = registerANewCustomer();
        ///Act
        mockMvc.perform(
                delete(REQUEST_PATH+ "/{id_customer}",String.valueOf(customerCreated.getIdCustomer()))
                        .accept(MediaType.APPLICATION_JSON))
                ///Assert
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given a valid Request to delete a Acquired Course by Customer, When its requested to delete the Acquired Course, Then response with the status OK")
    void Given_a_valid_Request_to_delete_a_Acquired_Course_by_Customer_When_its_requested_to_delete_the_Acquired_Course_Then_response_with_the_status_OK() throws Exception {

        ///Arrange
        Course courseToAcquire = registerANewCourse(teacher);
        AcquiredCourse acquiredCourse = registerANewAcquiredCourse(
                String.valueOf(customerCreatedToAcquireAndDesire.getIdCustomer()),
                courseToAcquire);
        ///Act
        mockMvc.perform(
                delete(REQUEST_PATH+ "/{id_customer}/acquire-course/{id_course}"
                        ,String.valueOf(customerCreatedToAcquireAndDesire.getIdCustomer())
                        ,String.valueOf(acquiredCourse.getCourse().getIdCourse()))
                        .accept(MediaType.APPLICATION_JSON))
                ///Assert
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given a valid Request to delete a Desired Course by Customer, When its requested to delete the Desired Course, Then response with the status OK")
    void Given_a_valid_Request_to_delete_a_Desired_Course_by_Customer_When_its_requested_to_delete_the_Desired_Course_Then_response_with_the_status_OK() throws Exception {

        ///Arrange
        Course courseToDesire = registerANewCourse(teacher);
        DesiredCourse desiredCourse =registerANewDesiredCourse(
                String.valueOf(customerCreatedToAcquireAndDesire.getIdCustomer()),
                courseToDesire
        );

        ///Act
        mockMvc.perform(
                delete(REQUEST_PATH+ "/{id_customer}/desire-course/{id_course}"
                        ,String.valueOf(customerCreatedToAcquireAndDesire.getIdCustomer())
                        ,String.valueOf(desiredCourse.getCourse().getIdCourse()))
                        .accept(MediaType.APPLICATION_JSON))
                ///Assert
                .andExpect(status().isOk());
    }

}