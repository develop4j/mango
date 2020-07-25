package com.levy.mango.admin.service;

import com.levy.mango.admin.model.SysDict;
import com.levy.mango.core.service.CrudService;

import java.util.List;

/**
 * 字典管理
 * @author levy
 * @date Jan 17,2020
 */
public interface SysDictService extends CrudService {
    /**
     * 根据标签名查询
     * @param label
     * @return
     */
    List<SysDict> findByLabel(String label);

}
