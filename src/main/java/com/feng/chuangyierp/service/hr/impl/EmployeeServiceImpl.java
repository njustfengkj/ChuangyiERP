package com.feng.chuangyierp.service.hr.impl;

import com.feng.chuangyierp.dao.hr.EmployeeDao;
import com.feng.chuangyierp.model.po.hr.Employee;
import com.feng.chuangyierp.service.hr.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    public Employee queryOne(long gh) {
        Map<String,Object> queryParam=new HashMap<String, Object>();
        queryParam.put("gh",gh);
        return employeeDao.queryOne(queryParam);
    }

    public List<Employee> batchQuery(Map queryParam) {
        return employeeDao.batchQuery(queryParam);
    }

    public List<Employee> queryOnJob(Map queryParam) {
        if(queryParam.containsKey("ryzt"))//如果包含人员状态这个参数
        {
            if(queryParam.get("ryzt").equals('1'))//如果人员状态参数设置的是在职
            {
                return employeeDao.batchQuery(queryParam);
            }
            else//如果人员状态参数设置的不是在职，那就先设置成在职然后再查询
            {
                queryParam.put("ryzt",'1');
                return employeeDao.batchQuery(queryParam);
            }
        }
        else//没有人员状态这个参数
        {
            queryParam.put("ryzt",'1');
            return employeeDao.batchQuery(queryParam);
        }
    }
}
