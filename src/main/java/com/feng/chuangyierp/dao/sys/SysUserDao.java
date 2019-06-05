package com.feng.chuangyierp.dao.sys;

import com.feng.chuangyierp.mapper.UserMapper;
import com.feng.chuangyierp.model.po.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysUserDao{
    @Autowired
    private UserMapper userMapper;

    public SysUser query(String name)
    {
        return userMapper.query(name);
    }
}
