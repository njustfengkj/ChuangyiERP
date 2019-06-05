//-------------------------------
//  功能介绍：新建1024*768窗口
//  参数说明：url URL地址;winname 窗口名称;scrollbars 是否有滚动条
//  返回值  ：窗口引用
//-------------------------------
function openwin1024(url,winname,scrollbars){
    var windowheight=screen.height;
    var windowwidth =screen.width;
    if(windowheight<700||windowwidth<1000){
        openwin800(url,winname,scrollbars);
    }else{
        windowheight=(windowheight-768)/2;
        windowwidth=(windowwidth-1024)/2;
        if(scrollbars==null)
            subwinname = window.open(url,winname,"resizable=yes,scrollbars=yes,status=yes,toolbar=no,menubar=no,location=no,directories=no,width=1014,height=680,top="+windowheight+",left="+windowwidth);
        else if(scrollbars=="no")
            subwinname = window.open(url,winname,"resizable=yes,scrollbars=no,status=yes,toolbar=no,menubar=no,location=no,directories=no,width=1014,height=680,top="+windowheight+",left="+windowwidth);
        else
            subwinname = window.open(url,winname,"resizable=yes,scrollbars=yes,status=yes,toolbar=no,menubar=no,location=no,directories=no,width=1014,height=680,top="+windowheight+",left="+windowwidth);
        subwinname.focus();
        return subwinname;
    }
}