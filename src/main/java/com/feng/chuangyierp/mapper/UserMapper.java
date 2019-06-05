package com.feng.chuangyierp.mapper;

import com.feng.chuangyierp.model.po.sys.SysUser;
import com.feng.chuangyierp.provider.SysUserProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @SelectProvider(type= SysUserProvider.class,method = "query")
    SysUser query(String name);
}
