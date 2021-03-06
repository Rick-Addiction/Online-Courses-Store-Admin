package com.coursesstore.admin.adapters.http.teacher.post;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8105"})
@AutoConfigureMockMvc
class PostTeacherControllerTest {

    final String REQUEST_PATH = "/courses-store/teacher";

    @Autowired
    private MockMvc mockMvc;

    Course newCourse;

    @BeforeEach
    public void setUp(){
        Teacher newTeacher = registerANewTeacher();
        newCourse = registerANewCourse(newTeacher);
    }

    @Test
    @DisplayName("Given a valid Request to post a Teacher, When its requested to create a Teacher, Then response with the status CREATED")
    void Given_a_valid_Request_to_post_a_Teacher_When_its_requested_to_create_a_Teacher_Then_response_with_the_status_CREATED() throws Exception {

        mockMvc.perform(
                post(REQUEST_PATH+ "/register")
                        .content(asJsonString(DomainUtils.generateRequestPostTeacher()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}