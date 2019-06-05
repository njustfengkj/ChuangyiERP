package com.feng.chuangyierp.service.hr;

import com.feng.chuangyierp.model.po.hr.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EmployeeService {
    public Employee queryOne(long gh);
    public List<Employee> batchQuery(Map queryParam);
    public List<Employee> queryOnJob(Map queryParam);
}
