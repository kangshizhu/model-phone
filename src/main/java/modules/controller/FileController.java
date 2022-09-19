package modules.controller;


import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import modules.dto.UsersDto;
import modules.dto.WeiXinLoginDTO;
import modules.entity.Users;
import modules.service.IFileService;
import modules.service.IUsersService;

import modules.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.List;

/**
 * <p>
 *  文件上传
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-16
 */
@Api(tags = "阿里云文件上传接口")
@RestController
@RequestMapping("/file")
public class FileController {
   @Resource
   IFileService iFileService;


    /**
     * 阿里云文件上传
     * @param request
     * @return
     */
    @ApiOperation("阿里云文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if (StringUtils.isNullOrEmpty(files.get(0).getName())) {
            return null;
        }
        System.out.println(files.get(0).getName()+"xxxxxxxxxxxx");
        // 获取文件后缀
        String prefix = files.get(0).getName().substring(files.get(0).getName().lastIndexOf("."));
        try {
            File file = File.createTempFile(files.get(0).getName(), prefix);
            return iFileService.upload(file);
        } catch (Exception e) {
            throw new Exception("文件转化失败");
        }
    }



}
