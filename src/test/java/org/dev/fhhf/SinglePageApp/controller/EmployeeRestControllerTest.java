package org.dev.fhhf.SinglePageApp.controller;

import org.dev.fhhf.SinglePageApp.model.Employee;
import org.dev.fhhf.SinglePageApp.service.EmployeeService;
import org.dev.fhhf.SinglePageApp.service.ReadAndParseJsonFile;
import org.dev.fhhf.SinglePageApp.service.ValidateInputDataService;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.List;

@RestClientTest
class EmployeeRestControllerTest {

    @Mock
    private EmployeeService employeeService;
    @Mock
    private ValidateInputDataService validateInputDataService;

    @InjectMocks
    private EmployeeRestController employeeRestController;

    private MockMvc mockMvc;

    private ReadAndParseJsonFile readAndParseJsonFile = new ReadAndParseJsonFile();

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(employeeRestController).build();
    }

    @Test
    public void shouldReturnTotalCountEmployees() throws Exception{

        when(employeeService.countTotalEmployees()).thenReturn(50);

        mockMvc.perform(
                get("/spapp/emp/totalEmployees")
            )
            .andExpect(status().isOk())
            .andExpect(content().json("50"))
            .andExpect(header().string("content-type", "application/json"));
    }

    @Test
    public void shouldMatchAllEmployees() throws Exception{

        String fileName = "json/EmployeeTestData.json";

        List<Employee> employees = readAndParseJsonFile.getParseEmployees(fileName);
        //System.out.println(employees);
        JSONArray employeesJson = readAndParseJsonFile.getJsonArray(fileName);

        when(employeeService.findAllEmployees()).thenReturn(employees);

        mockMvc.perform(
                get("/spapp/emp/all")
            )
            .andExpect(status().isOk())
            .andExpect(content().json(employeesJson.toJSONString()))
            .andExpect(header().string("content-type", "application/json"));
    }
}