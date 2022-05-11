package com.niuniu.hcs_discuz.interceptor;

import com.niuniu.hcs_discuz.utils.CommonUtil;
import com.niuniu.hcs_discuz.utils.TokenUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author niuniu
 * @date 2022/3/18
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    TokenUtil tokenUtil;

    //请求拦截器，验证是否登录
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果tokenUtil没有实例则从bean工厂获取
        if (null == tokenUtil) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            tokenUtil = (TokenUtil) factory.getBean("tokenUtil");
        }
        //请求的请求头里获取token
        String token = request.getHeader("token");
        //如果用户未登录或登录已超时，则拦截
        if (CommonUtil.strIsNull(token)) {
            request.getRequestDispatcher(request.getContextPath() + "/user/backlogin").forward(request, response);
            return false;
        }
        //如果用户已登录则重置token失效时间，并放行
        if (tokenUtil.checkToken(token)) {
            tokenUtil.setTokenExpire(token);  //重置token失效时间
            return true;
        } else {
            request.getRequestDispatcher(request.getContextPath() + "/user/backlogin").forward(request, response);
            return false;
        }
    }

}
