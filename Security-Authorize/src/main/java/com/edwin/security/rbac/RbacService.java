package com.edwin.security.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wenpuzhao on 2019/3/21.
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
