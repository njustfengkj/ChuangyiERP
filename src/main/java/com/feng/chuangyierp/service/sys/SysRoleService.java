package com.feng.chuangyierp.service.sys;

import com.feng.chuangyierp.model.po.sys.SysRole;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SysRoleService {
    public SysRole query(Map queryParam);
}
