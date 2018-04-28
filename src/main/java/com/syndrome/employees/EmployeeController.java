package com.syndrome.employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    static Map<Long, Employee> employees = Collections.synchronizedMap(new HashMap<Long, Employee>());

    @ResponseBody
    @GetMapping(value = "/employees")
    public List<Employee> getEmployees () {
        List<Employee> result = new ArrayList<Employee>(employees.values());
        return result;
    }

    @ResponseBody
    @PostMapping(value = "/employees")
    public String addEmployee ( @RequestParam("id") Long id,
                                @RequestParam("name") String name,
                                @RequestParam("age") Integer age,
                                @RequestParam("gender") String gender) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        employee.setGender(gender);

        employees.put(id,employee);
        return "success";
    }

    @RequestMapping("/")
    public ModelAndView index(){
        List<Employee> userList =new ArrayList<Employee>(employees.values());

        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }
}
