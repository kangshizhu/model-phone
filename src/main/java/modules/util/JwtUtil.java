package modules.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author Scott
 * @Date 2018-07-12 14:23
 * @Desc JWT工具类
 **/
public class JwtUtil {
    //设置过期时间
    //Token过期时间两天
    public static final long EXPIRE_DATE = 30 * 60 * 1000 * 96;

    // private static final long EXPIRE_DATE=30 * 60 * 1000 * 96;
    //token秘钥
    private static final String TOKEN_SECRET = "ZCfasfhuaUUHufguGuwu2020BQWE";

    public static String token (String username,String password){

        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_DATE);
            System.out.println(date);
            System.out.println(EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username",username)
                    .withClaim("password",password).withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return token;
    }

    /**
     * 生成签名,5min后过期
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);

    }

    /**
     * 根据request中的token获取用户账号
     *
     * @param request
     * @return
     * @throws
     */
    public static String getUserNameByToken(HttpServletRequest request) throws Exception {
        String accessToken = request.getHeader("X-Access-Token");
        String username = getUsername(accessToken);
        if (oConvertUtils.isEmpty(username)) {
            throw new Exception("未获取到用户");
        }
        return username;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getPassword(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("password").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static boolean verify(String token){
        /**
         * @desc   验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
    public static void main(String[] args) {
        String username ="zhangsan";
        String password = "123";
        String token = token(username,password);
        System.out.println(token);
        boolean b = verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMyIsImV4cCI6MTY0MTQ0MTE4NSwidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.uFbr2rpueyauBSpP8H_zlw_BOZmtBLApqD2T-AX4kig");
        System.out.println(b);
    }

}
