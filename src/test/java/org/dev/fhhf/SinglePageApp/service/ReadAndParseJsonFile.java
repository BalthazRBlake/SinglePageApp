package org.dev.fhhf.SinglePageApp.service;

import org.dev.fhhf.SinglePageApp.model.Department;
import org.dev.fhhf.SinglePageApp.model.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadAndParseJsonFile {

    public JSONArray getJsonArray(String fileName){

        JSONArray employeeList = null;
        JSONParser jsonParser = new JSONParser();

        File file = this.getFileFromResources(fileName);

        try (FileReader reader = new FileReader(file))
        {
            Object obj = jsonParser.parse(reader);

            employeeList = (JSONArray) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public List<Employee> getParseEmployees(String fileName){

        List<Employee> employees = new ArrayList<>();

        JSONArray employeeList = this.getJsonArray(fileName);
        employeeList.forEach( emp -> {
            employees.add(parseEmployeeObject( (JSONObject) emp ));
        });

        return employees;
    }

    private File getFileFromResources(String fileName){

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    private Employee parseEmployeeObject(JSONObject employee)
    {
        JSONObject employeeObject = (JSONObject) employee.clone();
        JSONObject departmentObject = (JSONObject) employeeObject.get("emp_dpId");

        long empId = (Long) employeeObject.get("empId");
        String empName = (String) employeeObject.get("empName");
        boolean empActive = (Boolean) employeeObject.get("empActive");

        long dpId = (Long) departmentObject.get("dpId");
        String dpName = (String) departmentObject.get("dpName");

        Department d = new Department((int) dpId, dpName);
        Employee e = new Employee((int) empId, empName, empActive,d);

        return e;
    }
}
