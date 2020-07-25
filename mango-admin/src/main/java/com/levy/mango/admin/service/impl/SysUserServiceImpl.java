package com.levy.mango.admin.service.impl;

import com.levy.mango.admin.model.SysMenu;
import com.levy.mango.admin.service.SysMenuService;
import com.levy.mango.common.utils.DateTimeUtils;
import com.levy.mango.common.utils.PoiUtils;
import com.levy.mango.core.page.MybatisPageHelper;
import com.levy.mango.core.page.PageRequest;
import com.levy.mango.core.page.PageResult;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.levy.mango.admin.mapper.SysUserMapper;
import com.levy.mango.admin.model.SysUser;
import com.levy.mango.admin.service.SysUserService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired(required = false)
    private SysUserMapper sysUserMapper;
    @Autowired(required = false)
    private SysMenuService sysMenuService;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }

    @Override
    public File createUserExcelFile(PageRequest pageRequest) {
        PageResult pageResult = findPage(pageRequest);
        return createUserExcelFile(pageResult.getContent());
    }

    @Override
    public SysUser findByName(String username) {
        return sysUserMapper.findByName(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        List<SysMenu> sysMenus = sysMenuService.findByUser(username);
        Set<String> perms = new HashSet<>();
        for (SysMenu sysMenu : sysMenus) {
            if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())){
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    public static File createUserExcelFile(List<?> records) {
        if (records == null){
            records = new ArrayList<>();
        }
        // 新建工作簿
        Workbook workbook = new XSSFWorkbook();
        // 新建sheet页
        Sheet sheet = workbook.createSheet();
        // 创建row
        Row row0 = sheet.createRow(0);
        int columnIndex = 0;
        // 创建标题单元格
        row0.createCell(columnIndex).setCellValue("No");
        row0.createCell(++columnIndex).setCellValue("ID");
        row0.createCell(++columnIndex).setCellValue("用户名");
        row0.createCell(++columnIndex).setCellValue("昵称");
        row0.createCell(++columnIndex).setCellValue("机构");
        row0.createCell(++columnIndex).setCellValue("角色");
        row0.createCell(++columnIndex).setCellValue("邮箱");
        row0.createCell(++columnIndex).setCellValue("手机号");
        row0.createCell(++columnIndex).setCellValue("状态");
        row0.createCell(++columnIndex).setCellValue("头像");
        row0.createCell(++columnIndex).setCellValue("创建人");
        row0.createCell(++columnIndex).setCellValue("创建时间");
        row0.createCell(++columnIndex).setCellValue("最后更新人");
        row0.createCell(++columnIndex).setCellValue("最后更新时间");
        // 遍历数据
        for (int i = 0; i < records.size(); i++) {
            SysUser sysUser = (SysUser) records.get(i);
            Row row = sheet.createRow(i+1);
            for (int j = 0; j < columnIndex+1; j++) {
                row.createCell(j);
            }
            columnIndex = 0;
            // 填充数据
            row.getCell(columnIndex).setCellValue(i+1);
            row.getCell(++columnIndex).setCellValue(sysUser.getId());
            row.getCell(++columnIndex).setCellValue(sysUser.getName());
            row.getCell(++columnIndex).setCellValue(sysUser.getNickName());
            row.getCell(++columnIndex).setCellValue(sysUser.getDeptName());
            row.getCell(++columnIndex).setCellValue(sysUser.getRoleNames());
            row.getCell(++columnIndex).setCellValue(sysUser.getEmail());
            row.getCell(++columnIndex).setCellValue(sysUser.getMobile());
            row.getCell(++columnIndex).setCellValue(sysUser.getStatus());
            row.getCell(++columnIndex).setCellValue(sysUser.getAvatar());
            row.getCell(++columnIndex).setCellValue(sysUser.getCreateBy());
            row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(sysUser.getCreateTime()));
            row.getCell(++columnIndex).setCellValue(sysUser.getLastUpdateBy());
            row.getCell(++columnIndex).setCellValue(DateTimeUtils.getDateTime(sysUser.getLastUpdateTime()));
        }
        return PoiUtils.createExcelFile(workbook,"download_user");
    }

    @Override
    public int save(SysUser record) {
        return 0;
    }

    @Override
    public int delete(SysUser record) {
        return 0;
    }

    @Override
    public int delete(List<SysUser> records) {
        return 0;
    }

    @Override
    public SysUser findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysUserMapper);
    }
}
