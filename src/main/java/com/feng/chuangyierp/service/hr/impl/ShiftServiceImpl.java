package com.feng.chuangyierp.service.hr.impl;

import com.feng.chuangyierp.dao.hr.ShiftDao;
import com.feng.chuangyierp.service.hr.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShiftServiceImpl implements ShiftService {
    @Autowired
    private ShiftDao shiftDao;

    public List batchQuery(Map queryParam) {
        return shiftDao.batchQuery(queryParam);
    }

    public void delete(Map param) {
        shiftDao.delete(param);
    }

    public void add(Map shift)
    {
        shiftDao.add(shift);
    }
}
