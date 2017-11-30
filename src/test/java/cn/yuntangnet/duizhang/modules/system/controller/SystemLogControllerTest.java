package cn.yuntangnet.duizhang.modules.system.controller;

import cn.yuntangnet.duizhang.DuizhangApplicationTests;
import cn.yuntangnet.duizhang.modules.system.entity.SystemLog;
import cn.yuntangnet.duizhang.modules.system.service.ISystemLogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 茂林
 * @since 2017/11/29 10:49
 */
@AutoConfigureMockMvc
public class SystemLogControllerTest extends DuizhangApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ISystemLogService systemLogService;

    @Test
    public void testList() throws Exception {
        mockMvc.perform(get("/system/systemLog/list").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //.andExpect(content().string(equalTo("hello world")));
    }

    @Test
    public void testAdd() {
        SystemLog log = new SystemLog();
        log.setUsername("张三");
        log.setTime(100L);
        log.setParams("123");
        log.setMethod("add");
        log.setCreateDate(new Date());
        log.setOperation("添加一个日志信息");
        log.setIp("127.0.0.1");

        systemLogService.insert(log);
    }
}