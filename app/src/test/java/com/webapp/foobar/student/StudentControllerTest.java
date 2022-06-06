package com.webapp.foobar.student;

import static org.hamcrest.CoreMatchers.containsString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

//        @Autowired
//        private StudentController controller;
//	@Test
//	void contextLoads() throws Exception {
//            assertThat(controller).isNotNull();
//	} 
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void shouldReturnStudents() throws Exception {
        this.mockMvc.perform(get("/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("nguyen1@gmail.com")));
    }

}
