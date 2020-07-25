package com.levy.mango.admin.service.impl;

import com.levy.mango.admin.constant.SysConstants;
import com.levy.mango.admin.mapper.SysMenuMapper;
import com.levy.mango.admin.model.SysMenu;
import com.levy.mango.admin.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired(required = false)
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<SysMenu> findByUser(String userName) {
        if(userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)){
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findByUsername(userName);
    }
}
