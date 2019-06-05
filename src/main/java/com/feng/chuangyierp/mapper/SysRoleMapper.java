package com.feng.chuangyierp.mapper;

import com.feng.chuangyierp.model.po.sys.SysRole;
import com.feng.chuangyierp.provider.SysRoleProvider;
import org.apache.ibatis.annotations.SelectProvider;

public interface SysRoleMapper {
    @SelectProvider(type = SysRoleProvider.class,method = "query")
    SysRole query(String id);
}
