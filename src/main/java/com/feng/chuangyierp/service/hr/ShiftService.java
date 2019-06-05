package com.feng.chuangyierp.service.hr;

import com.feng.chuangyierp.model.po.hr.Shift;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ShiftService {
    public List<Shift> batchQuery(Map queryParam);
    public void delete(Map param);
    public void add(Map shift);
}
