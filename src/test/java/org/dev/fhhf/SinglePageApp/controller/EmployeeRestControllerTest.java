package org.dev.fhhf.SinglePageApp.controller;

import org.dev.fhhf.SinglePageApp.model.Department;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;
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
    public void countTotalEmployeesTest() throws Exception {

        when(employeeService.countTotalEmployees()).thenReturn(50);

        mockMvc.perform(
                get("/spapp/emp/totalEmployees")
            )
            .andExpect(status().isOk())
            .andExpect(content().json("50"))
            .andExpect(header().string("content-type", "application/json"));
    }

    @Test
    public void findAllEmployeesTest() throws Exception {

        String fileName = "json/EmployeeTestData.json";

        List<Employee> employees = readAndParseJsonFile.getParseEmployees(fileName);

        JSONArray employeesJson = readAndParseJsonFile.getJsonArray(fileName);

        when(employeeService.findAllEmployees()).thenReturn(employees);

        mockMvc.perform(
                get("/spapp/emp/all")
            )
            .andExpect(status().isOk())
            .andExpect(content().json(employeesJson.toJSONString()))
            .andExpect(header().string("content-type", "application/json"));
    }

    @Test
    public void findPaginatedEmployeesTest() throws Exception {

        String fileName = "json/EmployeeTestData.json";

        List<Employee> employees = readAndParseJsonFile.getParseEmployees(fileName);

        JSONArray employeesJson = readAndParseJsonFile.getJsonArray(fileName);

        when(employeeService.findPaginatedEmployees(1,10))
                .thenReturn(employees);

        mockMvc.perform(
                get("/spapp/emp/paginated/1/10")
            )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(employeesJson.toJSONString()))
            .andExpect(header().string("content-type", "application/json"));
    }

    @Test
    public void findEmployeesNameStarsWithTest() throws Exception {

        Department dep = new Department(1, "HR");
        Employee employee1 = new Employee(1, "Mark", false, dep);
        Employee employee2 = new Employee(2, "Maria", true, dep);

        when(employeeService.findEmployeesNameStartsWith("ma"))
                .thenReturn(Arrays.asList(employee1,employee2));

        mockMvc.perform(
                get("/spapp/emp/search/ma")
            )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(
            "[\n" +
                            "{\n" +
                                "\"empId\":1,\n" +
                                "\"empName\":\"Mark\",\n" +
                                "\"empActive\":false,\n" +
                                "\"emp_dpId\":{\n" +
                                            "\"dpId\":1,\n" +
                                            "\"dpName\":\"HR\"\n"+
                                            "}\n" +
                            "},\n" +
                            "{\n" +
                                "\"empId\":2,\n" +
                                "\"empName\":\"Maria\",\n" +
                                "\"empActive\":true,\n" +
                                "\"emp_dpId\":{\n" +
                                        "\"dpId\":1,\n" +
                                        "\"dpName\":\"HR\"\n" +
                                        "}\n" +
                            "}\n" +
                        "]"
            ))
            .andExpect(header().string("content-type", "application/json"));
    }
}