package com.msw.devops.controller;

import com.msw.devops.entity.FastDFSFileEntity;
import com.msw.devops.exception.MyException;
import com.msw.devops.exception.Result;
import com.msw.devops.fastdfs.FastDFSClient;
import com.msw.devops.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 使用fastfds进行文件的上传、下载、删除管理
 */
@RestController
@Slf4j
public class UploadController {

    @PostMapping("/upload")
    public Result singleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new MyException(400, "上传文件不能为空");
        }
        JSONObject jsonObject = uploadFile(file);
        String fileName = file.getOriginalFilename();
        jsonObject.put("filename", fileName);
        return ResultUtil.success(200, jsonObject);
    }

    @DeleteMapping("/delete")
    public Result delete(String groupName, String remoteFileName) {
        try {
            int retcode = FastDFSClient.getInstance().deleteFile(groupName, remoteFileName);
            if (retcode != 0) {
                return ResultUtil.error(400, "删除失败");
            }
            return ResultUtil.success(200);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultUtil.error(400, "删除失败: " + e.toString());
        }
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response, String groupName, String remoteFileName) {
        response.setContentType("application/force-download");// 设置强制下载不打开
        String fileName = "test.jpg";    // 下载返回给客户端的文件名字，一般使用上传时的文件名来定义。从数据库查询
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        InputStream inputStream = FastDFSClient.getInstance().downFile(groupName, remoteFileName);

        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(inputStream);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @return path 文件访问路径
     * @Description 上传文件到 FastDFS
     * @Date 2018/10/29
     * @Param [file]
     * @Param [domainName] 域名
     **/
    public JSONObject uploadFile(MultipartFile file) throws IOException {

        String[] fileAbsolutePath = {};
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = file.getInputStream();
        if (inputStream != null) {
            int available = inputStream.available();
            file_buff = new byte[available];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFileEntity fastDFSFileEntity = new FastDFSFileEntity(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.getInstance().upload(fastDFSFileEntity);
            log.info(fileAbsolutePath.toString());
        } catch (Exception e) {
            log.error("upload file Exception!", e);
            throw new MyException(400, "文件上传出错" + e);
        }
        if (fileAbsolutePath == null) {
            log.error("upload file failed,please upload again!");
            throw new MyException(400, "文件上传失败，请重新上传");
        }
        String path = FastDFSClient.getInstance().getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        JSONObject upResultJson = new JSONObject();
        upResultJson.put("path", path);
        upResultJson.put("groupName", fileAbsolutePath[0]);
        upResultJson.put("remoteFileName", fileAbsolutePath[1]);

        return upResultJson;
    }
}
