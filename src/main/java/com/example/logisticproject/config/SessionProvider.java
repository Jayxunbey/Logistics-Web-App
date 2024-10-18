package com.example.logisticproject.config;

import com.example.logisticproject.enums.LanguageCodes;
import com.example.logisticproject.exceptions.classes.base.ValidationException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class SessionProvider {

    static final String AUTHORIZATION_HEADER = "Authorization";

    public static CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails details = (Objects.isNull(authentication) || isAnonymous(authentication)) ? null :
                (CustomUserDetails) authentication.getPrincipal();
        if (details == null) {
            throw new ValidationException("user.not.found");
        }
        return details;
    }

    private static boolean isAnonymous(Authentication authentication) {
        return authentication.getPrincipal().equals("anonymousUser");
    }

    public static LanguageCodes getLanguage() {
        return LanguageCodes.valueOf(LocaleContextHolder.getLocale().getLanguage().toUpperCase());
    }

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (!Objects.isNull(authentication) && !isAnonymous(authentication));
    }

    public static String getToken() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getRequest().getHeader(AUTHORIZATION_HEADER);
    }
}
