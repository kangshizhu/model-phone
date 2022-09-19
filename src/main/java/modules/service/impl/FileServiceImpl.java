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
    private  String accessKeyId= OssConstant.OSS_ACCESS_KEY_ID_IM;
    private  String accessKeySecret=OssConstant.OSS_ACCESS_KEY_SECRET_IM;
    private  String endpoint =  OssConstant.OSS_END_POINT_IM;
    private  String bucket = OssConstant.OSS_BUCKET_IM;

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public Result upload(File file) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //File file = new File(filename);
        String dateStr = format.format(new Date());
        if(null == file){
            return null;
        }
        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        try {
            //容器不存在，就创建
            if(!ossClient.doesBucketExist(bucket)){
                ossClient.createBucket(bucket);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
                createBucketRequest.setCannedACL(CannedAccessControlList.Default);
                ossClient.createBucket(createBucketRequest);
            }
            String fileUrl = dateStr + "/" + new Date().getTime()+"-"+file.getName();

            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucket, fileUrl,  file));
            //设置权限 这里是私有权限读写
            ossClient.setBucketAcl(bucket,CannedAccessControlList.Default);
            if(null != result){
                logger.info("==========>OSS文件上传成功,OSS地址："+fileUrl);
                return Result.OK(bucket+"/"+fileUrl);
            }
        }catch (OSSException oe){
            logger.error(oe.getMessage());
        }catch (ClientException ce){
            logger.error(ce.getMessage());
        }finally {
            //关闭
            ossClient.shutdown();
        }

        return Result.OK("文件路径");

    }
}
