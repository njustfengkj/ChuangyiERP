package com.feng.chuangyierp.ctrl.hr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "schedule")
public class ScheduleCtrl {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String getPage()
    {
        return null;
    }
}
