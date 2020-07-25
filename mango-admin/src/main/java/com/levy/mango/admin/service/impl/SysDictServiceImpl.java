package com.levy.mango.admin.service.impl;

import com.levy.mango.admin.mapper.SysDictMapper;
import com.levy.mango.admin.model.SysDict;
import com.levy.mango.admin.service.SysDictService;
import com.levy.mango.core.page.MybatisPageHelper;
import com.levy.mango.core.page.PageRequest;
import com.levy.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictServiceImpl implements SysDictService {

    @Autowired(required = false)
    SysDictMapper sysDictMapper;

    @Override
    public List<SysDict> findByLabel(String label) {
        return sysDictMapper.findByLabel(label);
    }

    @Override
    public int save(Object record) {
        return 0;
    }

    @Override
    public int delete(Object record) {
        return 0;
    }

    @Override
    public int delete(List records) {
        return 0;
    }

    @Override
    public Object findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if(label != null){
            return MybatisPageHelper.findPage(pageRequest,sysDictMapper,"findPageByLabel",label);
        }
        return MybatisPageHelper.findPage(pageRequest,sysDictMapper);
    }
}
