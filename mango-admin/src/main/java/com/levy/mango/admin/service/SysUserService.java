package com.levy.mango.admin.service;

import com.levy.mango.admin.model.SysUser;
import com.levy.mango.core.page.PageRequest;
import com.levy.mango.core.service.CrudService;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.File;
import java.util.List;
import java.util.Set;

public interface SysUserService extends CrudService<SysUser> {
    /**
     * 查询所有用户
     */
    List<SysUser> findAll();

    /**
     * 生成用户信心的Excel文件
     * @param pageRequest
     * @return
     */
    File createUserExcelFile(PageRequest pageRequest);

    /**
     * 根据名称查询用户
     * @param username
     * @return
     */
    public SysUser findByName(String username);

    /**
     * 查找用户的菜单权限标识集合
     * @param name
     * @return
     */
    Set<String> findPermissions(String name);
}

