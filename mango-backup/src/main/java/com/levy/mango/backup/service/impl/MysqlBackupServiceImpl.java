package com.levy.mango.backup.service.impl;

import com.levy.mango.backup.service.MysqlBackupService;
import com.levy.mango.backup.utils.MysqlBackupRestoreUtils;
import org.springframework.stereotype.Service;

@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {
    @Override
    public boolean backup(String host, String username, String password, String backupFolderPath, String fileName, String database) throws Exception {

        return MysqlBackupRestoreUtils.backup(host,username,password,backupFolderPath,fileName,database);
    }

    @Override
    public boolean restore(String restoreFilePath, String host, String userName, String password, String database) throws Exception {
        return MysqlBackupRestoreUtils.restore(restoreFilePath,host,userName,password,database);
    }
}
