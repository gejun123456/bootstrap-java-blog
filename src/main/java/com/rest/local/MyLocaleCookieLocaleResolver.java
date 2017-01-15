package com.rest.local;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @Author bruce.ge
 * @Date 2017/1/15
 * @Description
 */
public class MyLocaleCookieLocaleResolver extends CookieLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        parseLocaleCookieIfNecessary(request);
        return (Locale) request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME);
    }

    @Override
    public LocaleContext resolveLocaleContext(HttpServletRequest request) {
        parseLocaleCookieIfNecessary(request);
        return new TimeZoneAwareLocaleContext() {
            @Override
            public TimeZone getTimeZone() {
                return (TimeZone) request.getAttribute(TIME_ZONE_REQUEST_ATTRIBUTE_NAME);
            }

            @Override
            public Locale getLocale() {
                return (Locale) request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME);
            }
        };
    }


    @Override
    public void addCookie(HttpServletResponse response, String cookieValue) {
        super.addCookie(response, "%22" + cookieValue + "%22");
    }

    private void parseLocaleCookieIfNecessary(HttpServletRequest request) {
        if (request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME) == null) {
            Cookie cookie = WebUtils.getCookie(request, getCookieName());
            Locale locale = null;
            TimeZone timeZone = null;
            if (cookie != null) {
                String value = cookie.getValue();

                value = StringUtils.replace(value, "%22", "");

                String localePart = value;

                String timeZonePart = null;
                int spaceIndex = localePart.indexOf(" ");
                if (spaceIndex != -1) {
                    localePart = value.substring(0, spaceIndex);
                    timeZonePart = value.substring(spaceIndex + 1);
                }

                locale = (!"-".equals(localePart) ? StringUtils.parseLocaleString(localePart.replace("-", "_")) : null);
                if (timeZonePart != null) {
                    timeZone = StringUtils.parseTimeZoneString(timeZonePart);
                }

                if (logger.isTraceEnabled()) {
                    logger.trace("Parsed cookie vlaue [" + cookie.getValue() + "] into locale '" + locale + "'" + (timeZone != null
                            ? " and time zone '" + timeZone.getID() + "'" : ""));
                }
            }

            request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME, (locale != null ? locale : determineDefaultLocale(request)));

            request.setAttribute(TIME_ZONE_REQUEST_ATTRIBUTE_NAME, (timeZone != null ? timeZone : determineDefaultTimeZone(request)));

        }

    }


}
