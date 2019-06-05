package com.feng.chuangyierp.service.sys.impl;

import com.feng.chuangyierp.dao.sys.SysRoleDao;
import com.feng.chuangyierp.model.po.sys.SysRole;
import com.feng.chuangyierp.service.sys.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;

    public SysRole query(Map queryParam)
    {
        return sysRoleDao.query(queryParam);
    }
}
