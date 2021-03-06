package com.coursesstore.admin.adapters.http.course.delete;


import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.customer.Customer;
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
class DeleteCourseControllerTest {

    final String REQUEST_PATH = "/courses-store/course";

    Course courseCreated;
    @BeforeEach
    public void setUp(){
        courseCreated=registerANewCourse();
    }

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Given a valid Request to delete a Course, When its requested to delete a Course, Then response with the status OK")
    void Given_a_valid_Request_to_delete_a_Course_When_its_requested_to_delete_a_Course_Then_response_with_the_status_OK() throws Exception {
        ///Arrange, Act & Assert
        mockMvc.perform(
                delete(REQUEST_PATH+ "/{id_course}",String.valueOf(courseCreated.getIdCourse()))
                        .accept(MediaType.APPLICATION_JSON))
                ///Assert
                .andExpect(status().isOk());
    }

}