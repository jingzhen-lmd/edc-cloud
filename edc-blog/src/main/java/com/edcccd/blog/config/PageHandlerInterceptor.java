package com.edcccd.blog.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edcccd.blog.util.PageUtils;
import com.mysql.cj.util.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.edcccd.blog.config.Constant.*;

/**
 * 分页拦截器
 * 从前端请求中直接获取分页信息，分页查询时直接取出
 */
@Configuration
public class PageHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String currentPage = request.getParameter(CURRENT);
        String pageSize = Optional.ofNullable(request.getParameter(SIZE)).orElse(DEFAULT_SIZE);
        if (!StringUtils.isNullOrEmpty(currentPage)) {
            PageUtils.setCurrentPage(new Page<>(Long.parseLong(currentPage), Long.parseLong(pageSize)));
        }
        return true;
    }


    /**
     * 删除
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        PageUtils.remove();
    }
}