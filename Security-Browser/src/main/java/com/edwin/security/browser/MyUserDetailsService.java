package com.edwin.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by wenpuzhao on 2019/1/7.
 */

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名："+username);
        // 伪代码，根据用户名去数据库查找用户信息
        // 根据查找到到用户信息去判断用户是否被冻结
        String password=passwordEncoder.encode("123456");
//        String password = "123456";
        logger.info("登录密码："+password);
        return new User(username,password,true,true,true
                ,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
