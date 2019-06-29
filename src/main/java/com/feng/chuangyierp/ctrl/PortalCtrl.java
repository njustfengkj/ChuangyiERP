package com.feng.chuangyierp.ctrl;

import com.feng.chuangyierp.model.po.sys.SysUser;
import com.feng.chuangyierp.service.sys.SysRoleService;
import com.feng.chuangyierp.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/portal")
public class PortalCtrl {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value = "/getPortalPage")
    public String getPortalPage(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("name", userName);
        SysUser user = sysUserService.query(userName);
//        SysRole sysRole=null;
        if (null != user) {
            Map<String, Object> queryparam = new HashMap<String, Object>();
            queryParam.clear();
//            queryParam.put("id",user.getRoleid());
//            sysRole=sysRoleService.query(queryParam);
        }
        if (userName.equals(user.getName()) && password.equals(user.getPassword())) {
            request.getSession().setAttribute("userName", userName);
            request.getSession().setAttribute("userNickName", user.getNc());
//            request.getSession().setAttribute("roleName",sysRole.getName());
            return "portal.html/portal.html";
        } else {
            return "error";
        }
    }
}
