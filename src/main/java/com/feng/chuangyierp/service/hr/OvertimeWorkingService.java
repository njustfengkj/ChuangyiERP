package com.feng.chuangyierp.service.hr;

import com.feng.chuangyierp.model.po.hr.HrOvertimeWorking;
import com.feng.chuangyierp.model.vo.hr.OvertimeWorkingVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OvertimeWorkingService {
    public HrOvertimeWorking query(long gh);
    public List<OvertimeWorkingVo> batchQuery(Map queryParam);
    public void insert(HrOvertimeWorking hrOvertimeWorking);
    public void batchInsert(List<HrOvertimeWorking> hrOvertimeWorkings);
    public void delete(Map queryParam);
    public void update(Map updateParam);
}
