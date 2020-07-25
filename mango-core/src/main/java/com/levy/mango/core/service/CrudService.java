package com.levy.mango.core.service;

import com.levy.mango.core.page.PageRequest;
import com.levy.mango.core.page.PageResult;

import java.util.List;

/**
 * 通用CRUD接口
 * @author levy
 * @date Jan 17,2020
 * @param <T>
 */
public interface CrudService<T> {

    /**
     * 保存操作
     * @param record
     */
    int save(T record);

    /**
     * 删除操作
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 批量删除
     * @param records
     * @return
     */
    int delete(List<T> records);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 分页查询
     * @param pageRequest
     * @return
     */
    PageResult findPage(PageRequest pageRequest);
}
