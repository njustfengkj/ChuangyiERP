package com.feng.chuangyierp.service.hr.impl;

import com.feng.chuangyierp.dao.hr.EmployeeDao;
import com.feng.chuangyierp.dao.hr.OvertimeWorkingDao;
import com.feng.chuangyierp.model.po.hr.Employee;
import com.feng.chuangyierp.model.po.hr.HrOvertimeWorking;
import com.feng.chuangyierp.model.vo.hr.OvertimeWorkingVo;
import com.feng.chuangyierp.service.hr.OvertimeWorkingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OvertimeWorkingServiceImpl implements OvertimeWorkingService {
    @Autowired
    private OvertimeWorkingDao overtimeWorkingDao;
    @Autowired
    private EmployeeDao employeeDao;
    public HrOvertimeWorking query(long gh) {
        return overtimeWorkingDao.query(gh);
    }

    public List<OvertimeWorkingVo> batchQuery(Map queryParam) {
        List<HrOvertimeWorking> hrOvertimeWorkings=overtimeWorkingDao.batchQuery(queryParam);
        List<OvertimeWorkingVo> result=new ArrayList<OvertimeWorkingVo>();
        for(HrOvertimeWorking hrOvertimeWorking:hrOvertimeWorkings)
        {
            OvertimeWorkingVo overtimeWorkingVo=new OvertimeWorkingVo();
            BeanUtils.copyProperties(hrOvertimeWorking,overtimeWorkingVo);
            Map<String,Object> queryParam2=new HashMap<String, Object>();
            queryParam2.put("gh",hrOvertimeWorking.getGh());
            Employee employee=employeeDao.queryOne(queryParam2);
            overtimeWorkingVo.setXm(employee.getXm());
            result.add(overtimeWorkingVo);
        }
        return result;
    }

    public void insert(HrOvertimeWorking hrOvertimeWorking) {
        overtimeWorkingDao.insert(hrOvertimeWorking);
    }

    public void batchInsert(List<HrOvertimeWorking> hrOvertimeWorkings) {
        //插入的时候，保证同一天内，每个人只有一条申报记录，所以这里要做校验，同时，插入当天不在库中的人的名字
        //先把当天加班的人员都选出来
        Map<String,Object> queryParam=new HashMap<String, Object>();
        String sbjbkssj=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        queryParam.put("sbjbkssj",sbjbkssj);
        List<HrOvertimeWorking> declaredInDbs= overtimeWorkingDao.batchQuery(queryParam);
        List<HrOvertimeWorking> toAdd=new ArrayList<HrOvertimeWorking>();
        for(HrOvertimeWorking add:hrOvertimeWorkings)
        {
            boolean needToAdd=true;
            for(HrOvertimeWorking inDb:declaredInDbs)
            {
                if(add.getGh()==inDb.getGh())
                {
                    needToAdd=false;
                }
            }
            if(needToAdd)
            {
                toAdd.add(add);
            }
        }
        overtimeWorkingDao.batchInsert(toAdd);
    }

    public void delete(Map queryParam) {
        overtimeWorkingDao.delete(queryParam);
    }

    public void update(Map updateParam) {
        overtimeWorkingDao.update(updateParam);
    }
}
