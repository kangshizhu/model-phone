package modules.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import modules.dto.UsersDto;
import modules.dto.WeiXinLoginDTO;
import modules.entity.Users;
import modules.mapper.UsersMapper;
import modules.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import modules.util.*;
import modules.util.shiro.LoginUser;
import modules.vo.Result;
import org.apache.shiro.codec.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-16
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Resource
    UsersMapper usersMapper;
    @Autowired
    private RedisUtil redisUtil;
    //用户登录后返回token和用户信息
    @Override
    public Result<JSONObject> queryRuleUser(UsersDto usersDto) {
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        //String userpassword = PasswordUtil.encrypt("cloud", password,salt);
        queryWrapper.eq("phone",usersDto.getPhone());
        //查询用户是否存在
        Users userOne=usersMapper.selectOne(queryWrapper);
        Result<JSONObject> result=new Result<JSONObject>();
        Long date = new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_DATE).getTime();
        // 生成token
        System.out.println(userOne.getPhone()+ userOne.getId()+"fffffffffff");
        String token = JwtUtil.sign(userOne.getPhone(), userOne.getId());
        //redis存取用户信息和token
        LoginUser loginUser=new LoginUser();
        BeanUtils.copyProperties(userOne,loginUser);
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, loginUser);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, date);
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("userInfo", userOne);
        result.setResult(obj);
        return result;
    }
    /**
     * 微信免密码登录
     * @param weiXinLoginDTO
     * @return
     */
    @Override
    public Result weiXinLogin(WeiXinLoginDTO weiXinLoginDTO) {
        String request ="appid="+"wx6a0ea9f5cc242975" + "&secret=" + "4cce3159845f6edd880e21b7a3fdabd3" + "&js_code=" + weiXinLoginDTO.getCode() + "&grant_type=authorization_code";
        String content = HttpUtil.sendPost("https://api.weixin.qq.com/sns/jscode2session?", (request));
        JSONObject json_test = JSONObject.parseObject(content);
        //解密电话号码
        JSONObject obj=getPhoneNumber(json_test.get("session_key").toString(),weiXinLoginDTO.getEncryptedData(),weiXinLoginDTO.getIv());
        String sphone=obj.get("phoneNumber").toString();
        //Result<JSONObject> result = new Result<JSONObject>();
        if(obj.isEmpty() && sphone==null){
            return Result.error("登录失败");
        }else {
            LoginUser loginUser=new LoginUser();
            QueryWrapper queryWrapper=new QueryWrapper("phone",sphone);
            Users user=usersMapper.selectOne(queryWrapper);
            //账号存在则登录 不存在则创建
            if(user!=null){
                BeanUtils.copyProperties(user,loginUser);
            }else{
                 Users users=new Users();
                 usersMapper.insert(users);
            }

            return Result.ok("登录成功");
        }

    }

    private JSONObject getPhoneNumber(String session_key, String encryptedData, String iv) {

        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(session_key);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            //Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
