package com.base.controller.salary;

import com.base.bean.Employee;
import com.base.bean.ResultVO;
import com.base.bean.Salary;
import com.base.service.EmpService;
import com.base.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salary/sobcfg")
public class SalaryEmpController {
    @Autowired
    SalaryService salaryService;
    @Autowired
    EmpService empService;

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResultVO updateEmpSalary(Integer sid, Long eid) {
        if (salaryService.updateEmpSalary(sid, eid) == 1) {
            return ResultVO.success("修改成功!");
        }
        return ResultVO.error("修改失败!");
    }

    @RequestMapping(value = "/salaries", method = RequestMethod.GET)
    public List<Salary> salaries() {
        return salaryService.getAllSalary();
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public Map<String, Object> getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Map<String, Object> map = new HashMap<>();
        List<Employee> employeeByPage = empService.getEmployeeByPageShort(page, size);
        Long count = empService.getCountByKeywords("", null, null, null, null, null, null, null);
        map.put("emps", employeeByPage);
        map.put("count", count);
        return map;
    }
}
