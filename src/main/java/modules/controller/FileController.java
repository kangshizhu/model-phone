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

import modules.util.aliyunFile.AliyunOSSUtil;
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
     * @param files
     * @return
     */
    @ApiOperation("阿里云单个文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result upload(MultipartFile files) throws Exception {

        File file = null;
        try {
            //MultipartFile 转file
            String originalFilename = files.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0], filename[1]);    //注意下面的 特别注意！！！
            files.transferTo(file);
            file.deleteOnExit();
            //返回阿里云文件路径
            String url=AliyunOSSUtil.OSSUploadFile(file);
            return Result.OK(url);
        } catch (Exception e) {
            throw new Exception("文件转化失败");
        }
    }

}
