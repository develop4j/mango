package com.levy.mango.backup.service;

/**
 * MYSQL命令行备份恢复服务
 * @author levy
 * @Date Mar 14, 2020
 */
public interface MysqlBackupService {

    /**
     *
     * @param host 地址 可以是本机也可以是远程
     * @param username 数据库用户名
     * @param password 数据库密码
     * @param backupFolderPath 数据库备份的路径
     * @param fileName 备份的文件名
     * @param database 需要备份的数据库名称
     * @return
     * @throws Exception
     */
    public boolean backup(String host,String username,String password,String backupFolderPath,String fileName,String database) throws Exception;

    /**
     *
     * @param restoreFilePath 数据库备份的脚本路径
     * @param host IP地址
     * @param userName 数据库用户名
     * @param password 数据库密码
     * @param database 数据库名称
     * @return
     * @throws Exception
     */
    public boolean restore(String restoreFilePath,String host,String userName,String password,String database) throws Exception;
}
