package com.deepblue777.frame.action;

import com.alibaba.fastjson.JSON;
import com.deepblue777.frame.common.BaseResponse;
import com.deepblue777.frame.dao.FrameAttachinfoDAO;
import com.deepblue777.frame.domain.FrameAttachinfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2020-01-08 20:00]
 */

@RestController
@RequestMapping("/frame/attach")
public class AttachinfoAction {

    private static final Logger log = LoggerFactory.getLogger(AttachinfoAction.class);

    @Value("${nasshare}")
    private String NAS_PATH;

    @Autowired
    private FrameAttachinfoDAO frameAttachinfoDAO;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("clientinfo") String clientinfo,
                         @RequestParam("clienttag") String clienttag) {
        if (file.isEmpty()) {
            return JSON.toJSONString(new BaseResponse<>(1, "上传失败，请选择文件"));
        }

        if (StringUtils.isBlank(clientinfo)) {
            return JSON.toJSONString(new BaseResponse<>(1, "上传失败，clientinfo不能为null"));
        }
        if (StringUtils.isBlank(clienttag)) {
            return JSON.toJSONString(new BaseResponse<>(1, "上传失败，clienttag不能为null"));
        }

        FrameAttachinfo attachinfo = new FrameAttachinfo();

        String fileName = file.getOriginalFilename();
        attachinfo.setId(UUID.randomUUID().toString());
        attachinfo.setAttachname(file.getOriginalFilename());
        attachinfo.setFiletype(fileName.substring(fileName.lastIndexOf(".")));
        attachinfo.setContenttype(file.getContentType());
        attachinfo.setFilelength(file.getSize());
        attachinfo.setClientid(UUID.randomUUID().toString());
        attachinfo.setCreateTime(new Date());
        attachinfo.setClientinfo(clientinfo);
        attachinfo.setClienttag(clienttag);
        String format = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String pathDir = NAS_PATH + format + "/" +attachinfo.getId()+ "/";
        File pathDirFile = new File(pathDir);
        try {
            if (!pathDirFile.exists()){
                pathDirFile.mkdirs();
            }

            File dest = new File(pathDir + fileName);
            attachinfo.setFilepath(pathDir + fileName);
            file.transferTo(dest);
            // TODO 存入数据库
            frameAttachinfoDAO.insert(attachinfo);
            log.info("上传成功");
            return JSON.toJSONString(new BaseResponse<>(0, "上传成功", attachinfo));
        } catch (IOException e) {
            log.error(e.toString(), e);
            e.printStackTrace();
        }
        return JSON.toJSONString(new BaseResponse<>(1, "上传失败!"));
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") String id, HttpServletResponse response) {

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");

            if (StringUtils.isBlank(String.valueOf(id))) {
                response.setHeader("Content-Type", "application/json");
                String msg = JSON.toJSONString(new BaseResponse<>(0, "不存在该附件！"));
                outputStream.write(msg.getBytes());
                outputStream.close();
                response.flushBuffer();
                return;
            } else {
                FrameAttachinfo attachinfo;
                attachinfo = frameAttachinfoDAO.findByClientId(id);
                if (attachinfo == null) {
                    String msg = JSON.toJSONString(new BaseResponse<>(0, "不存在该附件！"));
                    outputStream.write(msg.getBytes());
                    outputStream.close();
                    response.flushBuffer();
                    return;
                }
                response.setContentType(attachinfo.getContenttype());
                response.setHeader("Content-Dispoition", "attachment,filename=" + attachinfo.getAttachname());
                FileInputStream inputStream = new FileInputStream(new File(attachinfo.getFilepath()));
                int length = inputStream.available();
                byte data[] = new byte[length];
                response.setContentLength(length);
                inputStream.read(data);
                outputStream.write(data);
                inputStream.close();
                outputStream.close();
                response.flushBuffer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
