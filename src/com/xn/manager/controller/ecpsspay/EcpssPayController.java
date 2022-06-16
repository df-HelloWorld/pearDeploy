package com.xn.manager.controller.ecpsspay;

import com.xn.common.controller.BaseController;
import com.xn.manager.model.channel.ChannelChangeModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description: 管理员-渠道金额变更纪录的Controller层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/ecpsspay")
public class EcpssPayController extends BaseController {
    private static Logger log = Logger.getLogger(EcpssPayController.class);



    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public List<Map<String,String>> dataList(HttpServletRequest request, HttpServletResponse response, ChannelChangeModel model) throws Exception {
        List<Map<String,String>>  list  =  new ArrayList<>();


        return list;
    }


}
