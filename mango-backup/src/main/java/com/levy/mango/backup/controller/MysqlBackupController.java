package com.levy.mango.backup.controller;

import com.levy.mango.backup.constants.BackupConstants;
import com.levy.mango.backup.datasource.BackupDataSourceProperties;
import com.levy.mango.backup.service.MysqlBackupService;
import com.levy.mango.backup.utils.HttpResult;
import com.levy.mango.common.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统数据备份与还原
 * @author levy
 * @Date Mar 14, 2020
 */
@RestController
@RequestMapping("/backup")
public class MysqlBackupController {

    @Autowired
    MysqlBackupService backupService;
    @Autowired
    BackupDataSourceProperties properties;

    /**
     * 数据库数据备份
     * @return
     */
    @GetMapping("/backup")
    public HttpResult backup(){
        String backFolderName = BackupConstants.DEFAULT_BACKUP_NAME + "_" + (new SimpleDateFormat(BackupConstants.DATE_FORMAT)).format(new Date());
        return backup(backFolderName);
    }

    private HttpResult backup(String backFolderName) {
        String host = properties.getHost();
        String username = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String backupFolderPath = BackupConstants.BACKUP_FOLDER + backFolderName + File.separator;
        String fileName = BackupConstants.BACKUP_FILE_NAME;
        try{
            boolean success = backupService.backup(host, username, password, backupFolderPath, fileName, database);
            if(!success){
                HttpResult.error("数据备份失败！");
            }
        } catch (Exception e) {
            return HttpResult.error(500,e.getMessage());
        }
        return HttpResult.ok();
    }

    /**
     * 数据库数据恢复
     * @param name
     * @return
     */
    @GetMapping("/restore")
    public HttpResult restore(@RequestParam String name){
        String host = properties.getHost();
        String username = properties.getUserName();
        String password = properties.getPassword();
        String database = properties.getDatabase();
        String restoreFilePath = BackupConstants.RESTORE_FOLDER + name+File.separator+BackupConstants.BACKUP_FILE_NAME;
        try{
            boolean restoreSuccess = backupService.restore(restoreFilePath, host, username, password, database);
            if (restoreSuccess){
                return HttpResult.ok();
            }
        }catch (Exception e){
            return HttpResult.error(500,e.getMessage());
        }
        return HttpResult.ok();
    }

    /**
     * 备份查找接口
     * @return
     */
    @GetMapping("/findRecords")
    public HttpResult findBackupRecords(){
        if(!new File(BackupConstants.DEFAULT_RESTORE_FILE).exists()){
            //初始默认备份文件
            backup(BackupConstants.DEFAULT_BACKUP_NAME);
        }
        List<Map<String,String>> backRecords = new ArrayList<>();
        File recordFolderFile = new File(BackupConstants.RESTORE_FOLDER);
        if(recordFolderFile.exists()){
            for (File file:recordFolderFile.listFiles()){
                Map<String,String> backup = new HashMap<>();
                backup.put("name",file.getName());
                backup.put("title",file.getName());
                if(BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(file.getName())){
                    backup.put("title","系统默认备份");
                }
                backRecords.add(backup);
            }
        }
        // 排序，默认备份最前，然后按时间戳排序，新备份在前面
        backRecords.sort((o1,o2) -> BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o1.get("name")) ? -1 :
                BackupConstants.DEFAULT_BACKUP_NAME.equalsIgnoreCase(o2.get("name")) ? 1 :
                o2.get("name").compareTo(o1.get("name")));
        return HttpResult.ok(backRecords);
    }

    /**
     * 备份删除接口
     */
    @GetMapping("/delete")
    public HttpResult deleteBackupRecords(@RequestParam String name){
        if(BackupConstants.DEFAULT_BACKUP_NAME.equals(name)){
            return HttpResult.error("系统默认备份无法删除！");
        }
        String restoreFilePath = BackupConstants.BACKUP_FOLDER + name;
        try{
            FileUtils.deleteFile(new File(restoreFilePath));
        }catch (Exception e){
            return HttpResult.error(500,e.getMessage());
        }
        return HttpResult.ok();
    }

}
