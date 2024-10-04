package com.nbe2.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SecurityFilterConfiguration {

    // WebSecurityCustomizer를 쓰기 위해서는 Servlet Filter에서도 수행되지 않아야 함.
    // 이를 비호라성화 해주는 코드
    @Bean
    public FilterRegistrationBean<CustomSecurityFilter> registrationBean(
            CustomSecurityFilter customSecurityFilter) {
        FilterRegistrationBean<CustomSecurityFilter> registrationBean =
                new FilterRegistrationBean<>(customSecurityFilter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }
}
