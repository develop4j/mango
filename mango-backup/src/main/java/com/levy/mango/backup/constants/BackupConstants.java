package com.levy.mango.backup.constants;

import java.io.File;

/**
 * 常量类
 * @author levy
 * @Date Mar 15,2020
 */
public interface BackupConstants {
    /**
     * 备份的目录名称
     */
    public static final String BACKUP_FOLDER_NAME = "_mango_backup";
    /**
     * 备份的目录
     * System.getProperty("user.name")
     */
    public static final String BACKUP_FOLDER = "D:"+File.separator+"BackupDatabase" + File.separator+BACKUP_FOLDER_NAME+File.separator;
    /**
     * 还原目录，默认就是备份目录
     */
    public static final String RESTORE_FOLDER = BACKUP_FOLDER;
    /**
     * 日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd_HHmmss";
    /**
     * sql扩展名
     */
    public static final String SQL_EXT = ".sql";
    /**
     * 默认备份的文件名
     */
    public static final String BACKUP_FILE_NAME = "mango"+SQL_EXT;
    /**
     * 默认备份还原的目录名称
     */
    public static final String DEFAULT_BACKUP_NAME = "backup";
    /**
     * 默认备份还原文件
     */
    public static final String DEFAULT_RESTORE_FILE = BACKUP_FOLDER + DEFAULT_BACKUP_NAME+File.separator+BACKUP_FILE_NAME;
}
