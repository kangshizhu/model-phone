package modules.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import modules.dto.UsersDto;
import modules.dto.WeiXinLoginDTO;
import modules.entity.Users;
import modules.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * <p>
 *  文件上传
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-16
 */
public interface IFileService  {


    Result upload(File file);
}
