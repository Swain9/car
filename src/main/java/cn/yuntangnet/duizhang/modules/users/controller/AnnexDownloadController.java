package cn.yuntangnet.duizhang.modules.users.controller;

import cn.yuntangnet.duizhang.modules.users.entity.WorkOrder;
import cn.yuntangnet.duizhang.modules.users.service.IWorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 张茂林
 * @since 2017/12/17 21:41
 */
@Controller
public class AnnexDownloadController {

    private String upload = "C:/upload/";

    @Autowired
    private IWorkOrderService workOrderService;

    @RequestMapping("/users/wo/download/{id}")
    public void downloadResource(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {

        WorkOrder workOrder = workOrderService.selectById(id);

        Path file = Paths.get(upload, workOrder.getAnnex());
        if (Files.exists(file)) {
            String annex = workOrder.getAnnex();
            String filename = workOrder.getAgentArea() + workOrder.getUserPhone() + annex.substring(annex.lastIndexOf("."));
            //String fileName = "sunny.pdf";
            //response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            //如果文件名有中文的话，进行URL编码，让中文正常显示
            try {
                response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
                Files.copy(file, response.getOutputStream());
            } catch (IOException ex) {
            }
        }
    }

}
