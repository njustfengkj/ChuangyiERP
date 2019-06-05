package com.feng.chuangyierp.service.sys.impl;

import com.feng.chuangyierp.dao.sys.SysUserDao;
import com.feng.chuangyierp.model.po.sys.SysUser;
import com.feng.chuangyierp.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    public SysUser query(String name)
    {
        return sysUserDao.query(name);
    }
}
