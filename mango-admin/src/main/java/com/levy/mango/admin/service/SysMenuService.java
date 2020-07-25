package com.levy.mango.admin.service;

import com.levy.mango.admin.model.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * 菜单信息
 * @author levy
 * @date Jan 18,2020
 */
public interface SysMenuService {
    /**
     * 查询用户菜单权限信息
     * @param name
     * @return
     */
    List<SysMenu> findByUser(String name);
}
