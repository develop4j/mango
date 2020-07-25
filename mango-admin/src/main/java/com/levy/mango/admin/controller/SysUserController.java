package com.levy.mango.admin.controller;

import com.levy.mango.admin.model.SysUser;
import com.levy.mango.admin.service.SysUserService;
import com.levy.mango.common.utils.FileUtils;
import com.levy.mango.core.http.HttpResult;
import com.levy.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;



    /**
     * 用户数据导出
     * @param pageRequest
     * @param res
     */
    @PostMapping(value = "/exportExcelUser")
    public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res){
        File file = sysUserService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(res,file,file.getName());
    }
    /**
     * 查询所有数据
     * @return
     */
    @GetMapping(value = "/findAll")
    public List<SysUser> findAll(){
        return sysUserService.findAll();
    }

    /**
     * 分页查询
     * @param pageRequest
     * @return
     */
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok(sysUserService.findPage(pageRequest));
    }
}
