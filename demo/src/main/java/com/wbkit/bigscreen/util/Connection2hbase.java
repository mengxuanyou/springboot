package com.wbkit.bigscreen.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 连接hbase 获得Htable
 * 
 * @author duzhou.xu
 * 
 */
public class Connection2hbase {
    static Configuration HBASE_CONFIG = null;
    static Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(Connection2hbase.class);
   
    static { 
        HBASE_CONFIG = HBaseConfiguration.create();
        HBASE_CONFIG.addResource("hbase-site.xml");
        try {
            conn = ConnectionFactory.createConnection(HBASE_CONFIG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection2hbase() {
    }

    public static HTable getHTable(String tablename) {
        HTable table = null;
        try {
            table = (HTable) conn.getTable(TableName.valueOf(tablename));
        } catch (IOException e) {
            logger.info("Connection2hbase", e);
        }

        return table;
    }
}
