package com.feng.chuangyierp.ctrl.hr;

import com.alibaba.fastjson.JSON;
import com.feng.chuangyierp.model.po.hr.Shift;
import com.feng.chuangyierp.service.hr.ShiftService;
import com.feng.chuangyierp.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 班次信息管理的控制类
 */
@Controller
@RequestMapping(value = "/shift")
public class ShiftCtrl {
    @Autowired
    private ShiftService shiftService;

    @RequestMapping(value = "/getEditPage",method = RequestMethod.GET)
    public String getEditPage(HttpServletRequest request, HttpServletResponse response)
    {
        String id=request.getParameter("bcid");
        Map<String,String> queryParam=new HashMap<String, String>();
        queryParam.put("id",id);
        List<Shift> queryResults=shiftService.batchQuery(queryParam);
        Shift queryResult=queryResults.get(0);
        request.setAttribute("bcmc",queryResult.getBcmc());
        request.setAttribute("sbkssj",queryResult.getSbkssj());
        request.setAttribute("sbjssj",queryResult.getSbjssj());
        request.setAttribute("ksxxsj",queryResult.getKsxxsj());
        request.setAttribute("jsxxsj",queryResult.getJsxxsj());
        if(queryResult.getSfcr()!=0)
        {
            request.setAttribute("sfcr","true");
        }
        else
        {
            request.setAttribute("sfcr","false");
        }
        return "/hr/shift/shiftEdit";
    }

    @RequestMapping(value="/getMainPage")
    public String getShiftPage(HttpServletRequest request,HttpServletResponse response)
    {
        Map<String,Object> queryParam=new HashMap<String,Object>();
        List<Shift> shifts=shiftService.batchQuery(queryParam);
        request.setAttribute("shifts",shifts);
        return "/hr/shift/shiftMain";
    }

    @RequestMapping(value = "/batchQuery",method = RequestMethod.GET)
    public void batchQuery(HttpServletRequest request, HttpServletResponse response)
    {
        Map<String,Object> queryParam=new HashMap<String,Object>();
        String id=request.getParameter("id");
        String sbkssj=request.getParameter("sbkssj");
        String sbjssj=request.getParameter("sbjssj");
        String ksxxsj=request.getParameter("ksxxsj");
        String jsxxsj=request.getParameter("jsxxsj");
        String sfcr=request.getParameter("sfcr");
        if(!StringUtil.isEmpty(id))
        {
            queryParam.put("id",Long.parseLong(id));
        }
        if(!StringUtil.isEmpty(sbkssj))
        {
            queryParam.put("sbkssj",sbjssj);
        }
        if(!StringUtil.isEmpty(sbjssj))
        {
            queryParam.put("sbjssj",sbjssj);
        }
        if(!StringUtil.isEmpty(ksxxsj))
        {
            queryParam.put("ksxxsj",ksxxsj);
        }
        if(!StringUtil.isEmpty(jsxxsj))
        {
            queryParam.put("jsxxsj",jsxxsj);
        }
        if(!StringUtil.isEmpty(sfcr))
        {
            queryParam.put("sfcr",Integer.parseInt(sfcr));
        }
        List<Shift> shifts= shiftService.batchQuery(queryParam);
        String resultJson= JSON.toJSONString(shifts);
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer=response.getWriter();
            writer.write(resultJson);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="",method = RequestMethod.DELETE)
    public void delete(HttpServletRequest request,HttpServletResponse response)
    {
        Map<String,Object> param=new HashMap<String,Object>();
        String id=request.getParameter("id");
        int idLong=Integer.parseInt(id);
        param.put("id",idLong);
        shiftService.delete(param);
    }

    @RequestMapping(value = "/getAddPage",method = RequestMethod.GET)
    public String getAddPage(HttpServletRequest request,HttpServletResponse response)
    {
        return "/hr/shift/shiftAdd";
    }

    @RequestMapping(value = "", method=RequestMethod.POST)
    public void add(HttpServletRequest request,HttpServletResponse response)
    {
        Shift shiftToAdd=new Shift();
        String bcmc=request.getParameter("bcmc");
        String sbkssj=request.getParameter("sbkssj");
        String sbjssj=request.getParameter("sbjssj");
        String ksxxsj=request.getParameter("ksxxsj");
        String jsxxsj=request.getParameter("jsxxsj");
        String sfcr=request.getParameter("sfcr");
        Map<String,String> param=new HashMap<String, String>();
        param.put("bcmc",bcmc);
        param.put("sbkssj",sbkssj);
        param.put("sbjssj",sbjssj);
        param.put("ksxxsj",ksxxsj);
        param.put("jsxxsj",jsxxsj);
        if(Boolean.parseBoolean(sfcr))
        {
            param.put("sfcr","1");
        }
        else
        {
            param.put("sfcr","0");
        }
//        shiftToAdd.setBcmc(bcmc);
//        shiftToAdd.setSbkssj(Timestamp.valueOf(sbkssj));
//        shiftToAdd.setSbjssj(Timestamp.valueOf(sbjssj));
//        shiftToAdd.setKsxxsj(Timestamp.valueOf(ksxxsj));
//        shiftToAdd.setJsxxsj(Timestamp.valueOf(jsxxsj));
//        shiftToAdd.setSfcr(Long.parseLong(sfcr));
        shiftService.add(param);
    }
}
