package com.edcccd.blog.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Objects;

/**
 * 分页工具类
 * 由拦截器直接将分页数据放入，用的时候直接取出
 **/
public class PageUtils {

    private static final ThreadLocal<Page<?>> PAGE_HOLDER = new ThreadLocal<>();

    /**
     * 会删除记录，只保留页数信息
     */
    public static void setCurrentPage(Page<?> page) {
        PAGE_HOLDER.set(page);
    }

    public static <T> Page<T> getPage(Class<T> tClass) {
        Page<?> page = PAGE_HOLDER.get();
        if (Objects.isNull(page)) {
            page = new Page<>();
            setCurrentPage(page);
        }
        return new Page<>(page.getCurrent(), page.getSize());
    }

    public static void remove() {
        PAGE_HOLDER.remove();
    }

}
