package com.levy.mango.admin.controller;

import com.levy.mango.admin.model.SysDict;
import com.levy.mango.admin.service.SysDictService;
import com.levy.mango.core.http.HttpResult;
import com.levy.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dict")
public class SysDictController {

    @Autowired
    SysDictService sysDictService;

    /**
     * 保存操作
     * @param record
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sys:dict:add') AND hasAnyAuthority('sys:dict:edit')")
    @PostMapping(value = "/save")
    public HttpResult save(@RequestBody SysDict record){
        return HttpResult.ok(sysDictService.save(record));
    }

    /**
     * 删除操作
     * @param records
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sys:dict:delete')")
    @PostMapping(value = "/delete")
    public HttpResult delete(@RequestBody List<SysDict> records){
        return HttpResult.ok(sysDictService.delete(records));
    }

    /**
     * 分页查询
     * @param pageRequest
     * @return
     */
    @PreAuthorize("hasAnyAuthority('sys:dict:view')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysDictService.findPage(pageRequest));
    }

    @PreAuthorize("hasAnyAuthority('sys:dict:view')")
    @PostMapping(value = "/findByLabel")
    public HttpResult findByLabel(@RequestParam String label){
        return HttpResult.ok(sysDictService.findByLabel(label));
    }
}
