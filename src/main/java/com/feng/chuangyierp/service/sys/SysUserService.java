package com.feng.chuangyierp.service.sys;

import com.feng.chuangyierp.model.po.sys.SysUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SysUserService {
    public SysUser query(String name);
}
