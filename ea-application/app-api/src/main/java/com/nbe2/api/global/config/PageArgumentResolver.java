package com.nbe2.api.global.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.nbe2.common.annotation.PageDefault;
import com.nbe2.common.dto.Page;

public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageDefault.class)
                && parameter.getParameterType().equals(Page.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory)
            throws Exception {
        PageDefault pageDefault = parameter.getParameterAnnotation(PageDefault.class);

        String pageParam = webRequest.getParameter("page");
        int page = (pageParam == null) ? pageDefault.page() : Integer.parseInt(pageParam);

        String sizeParam = webRequest.getParameter("size");
        int size = (sizeParam == null) ? pageDefault.size() : Integer.parseInt(sizeParam);

        return new Page(page, size);
    }
}
