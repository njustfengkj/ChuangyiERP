package com.feng.chuangyierp.provider;

import org.apache.ibatis.jdbc.SQL;

public class SysUserProvider {
    private static final String TABLE_NAME = "sys_user";

    public String query(String name) {
        return new SQL() {{
            SELECT("*");
            FROM(TABLE_NAME);
            WHERE("name=#{name,jdbcType=VARCHAR}");
        }}.toString();
    }
}
