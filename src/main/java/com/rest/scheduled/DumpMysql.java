package com.rest.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.security.CodeSource;

/**
 * Created by bruce.ge on 2016/11/21.
 */
public class DumpMysql {

    private static Logger logger = LoggerFactory.getLogger(DumpMysql.class);
    public static void BackupDbtosql() {
        try {
            CodeSource codeSource = DumpMysql.class.getProtectionDomain().getCodeSource();
            File file = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = file.getParentFile().getPath();

            String dbName = "world";

            String dbUser = "root";

            String dbPass = "root";

            String folderPath = jarDir + "/backup";

            File f1 = new File(folderPath);
            f1.mkdir();

            String savePth = "/" + jarDir + "/backup/" + "backup.sql";

            String executeCommand = "mysqldump -h 192.168.0.103 -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePth;

            Process exec = Runtime.getRuntime().exec(executeCommand);
            int processComplete = exec.waitFor();
            if (processComplete == 0) {
                System.out.println("BackUp Complete");
            } else {
                System.out.println("BackUp failure");
            }

        } catch (Exception e) {
            logger.info("catch exception",e);
        }
    }
//
//    public static void main(String[] args) {
//        BackupDbtosql();
//    }
}
