package com.feng.chuangyierp.ctrl.hr;

import com.alibaba.fastjson.JSON;
import com.feng.chuangyierp.model.po.hr.Employee;
import com.feng.chuangyierp.model.po.hr.HrOvertimeWorking;
import com.feng.chuangyierp.model.vo.hr.OvertimeWorkingVo;
import com.feng.chuangyierp.service.hr.EmployeeService;
import com.feng.chuangyierp.service.hr.OvertimeWorkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 员工加班管理的控制类
 */
@Controller
@RequestMapping(value = "/overtimeWorking")
public class OvertimeWorkingCtrl {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OvertimeWorkingService overtimeWorkingService;

    @RequestMapping(value = "/getEditPage")
    public String getEditPage(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String,Object> queryParam=new HashMap();
        List<Employee> employees=employeeService.queryOnJob(queryParam);
        request.setAttribute("employees",employees);
        request.setAttribute("employeeService",employeeService);
        return "/hr/overtimeWorking/declarationForOvertimeWorking";
    }

    @RequestMapping(value = "/insert",method = {RequestMethod.POST})
    public void insert(HttpServletRequest request, HttpServletResponse response)
    {
        String jbrysStr=request.getParameter("jbry");
        String jbkssj=request.getParameter("jbkssj");
        String jbjssj=request.getParameter("jbjssj");
        String[] jbrys=jbrysStr.split(",");
        List<HrOvertimeWorking> hrOvertimeWorkings=new ArrayList<HrOvertimeWorking>();
        for(String jbry:jbrys)
        {
            HrOvertimeWorking hrOvertimeWorking=new HrOvertimeWorking();
            hrOvertimeWorking.setGh(Integer.parseInt(jbry));
            if(null!=jbkssj)
            {
                hrOvertimeWorking.setSbjbkssj(jbkssj);
            }
            if(null!=jbjssj)
            {
                hrOvertimeWorking.setSbjbjssj(jbjssj);
            }
            hrOvertimeWorkings.add(hrOvertimeWorking);
        }
        overtimeWorkingService.batchInsert(hrOvertimeWorkings);
    }

    @RequestMapping(value = "/batchQuery",method = {RequestMethod.GET})
    public void batchQuery(HttpServletRequest request,HttpServletResponse response)
    {
        String sbjbkssj=request.getParameter("jbkssj");
        if(sbjbkssj==null||"".equals(sbjbkssj))
        {
            sbjbkssj=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        else
        {
            sbjbkssj=sbjbkssj.substring(0,10);
        }
        Map<String,Object> queryParam=new HashMap<String, Object>();
        queryParam.put("sbjbkssj",sbjbkssj);
        List<OvertimeWorkingVo> hrOvertimeWorkings= overtimeWorkingService.batchQuery(queryParam);
        String resultJson= JSON.toJSONString(hrOvertimeWorkings);
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(resultJson);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/declaration",method = {RequestMethod.DELETE})
    public void declarationDelete(HttpServletRequest request,HttpServletResponse response)
    {
        Map<String,Object> queryParam=new HashMap<String, Object>();
        String strGh=request.getParameter("gh");
        int gh=Integer.parseInt(strGh);
        queryParam.put("gh",gh);
        String sbrq=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        queryParam.put("sbrq",sbrq);
        overtimeWorkingService.delete(queryParam);
    }

    @RequestMapping(value = "/declaration",method = RequestMethod.PUT)
    public void declarationUpdate(HttpServletRequest request,HttpServletResponse response)
    {
        HrOvertimeWorking updateParam=new HrOvertimeWorking();
        String gh=request.getParameter("gh");
        String sbjbkssj=request.getParameter("sbjbkssj");
        String sbjbjssj=request.getParameter("sbjbjssj").substring(0,19);
        if(sbjbkssj==null||"".equals(sbjbkssj))
        {
            sbjbkssj=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
        updateParam.setGh(Long.parseLong(gh));
        updateParam.setSbjbkssj(sbjbkssj);
        updateParam.setSbjbjssj(sbjbjssj);
        Map<String,Object> param=new HashMap<String, Object>();
        param.put("gh",updateParam.getGh());
        param.put("sbjbkssj",sbjbkssj);
        param.put("sbjbjssj",sbjbjssj);
        overtimeWorkingService.update(param);
    }
}
