package modules.service.impl;


import modules.service.IFileService;
import modules.util.aliyunFile.OssConstant;
import modules.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.Date;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;


import java.text.SimpleDateFormat;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-16
 */
@Service
public class FileServiceImpl  implements IFileService {

}
