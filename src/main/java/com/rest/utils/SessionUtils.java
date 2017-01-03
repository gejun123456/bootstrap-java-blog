package com.rest.utils;

import com.rest.bean.User;
import com.rest.constant.SessionConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by bruce.ge on 2017/1/4.
 */
public class SessionUtils {
    public static User getCurrentUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        Object attribute = session.getAttribute(SessionConstants.USER);
        if (attribute != null && attribute instanceof User) {
            return (User) attribute;
        }
        return null;
    }
}
