package com.niuniu.hcs_discuz.utils;

import com.niuniu.hcs_discuz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author niuniu
 * @date 2022/3/1
 */
@Component
public class TokenUtil {

    @Autowired
    RedisCache redisCache;

    public String createToken(User user) {
        String token = UUID.randomUUID().toString().replace("-", "");
        redisCache.set(token, user, 60 * 30);
        return token;
    }

    public boolean checkToken(String token) {
        return redisCache.hasKey(token) ? true : false;
    }

    public boolean setTokenExpire(String token) {
        redisCache.expire(token, 60 * 30);
        return true;
    }

    public User getCacheUser(String token) {
        return (User) redisCache.get(token);
    }

    public void setToken(String token, User user) {
        redisCache.set(token, user, 60 * 30);
    }

    public void deleteToken(String token) {
        redisCache.del(token);
    }

//    public String createToken(String username){
//        String token = UUID.randomUUID().toString().replace("-", "");
//        redisCache.set(username, token, 60*30);
//        return token;
//    }
//
//    public boolean checkToken(String username,String token){
//        String tokenCache = (String) redisCache.get(username);
//        if (token.equals(tokenCache)) return true;
//        else return false;
//    }
//
//    public String getToken(String username){
//        return (String) redisCache.get(username);
//    }
//
//    public void deleteToken(String username){
//        redisCache.del(username);
//    }

}
