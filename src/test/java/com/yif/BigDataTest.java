/**
 * 6006.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.yif;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author heyif
 * @since v1.0 2018-12-02T14:03
 */
public class BigDataTest {
    private long begin = 1;//起始id
    private long end = begin+100000;//每次循环插入的数据量
    private String url = "jdbc:mysql://127.0.0.1:3306/spring_shiro?useServerPrepStmts=false&rewriteBatchedStatements=true&useUnicode=true&amp;characterEncoding=UTF-8";
    private String user = "root";
    private String password = "a123456";

    @Test
    public void insertBigData(){
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            //加载jdbc驱动
            Class.forName("com.mysql.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //关闭自动提交
//            conn.setAutoCommit(false);
            String sql = "insert into test_user(name,sex,age,email,tel,address) values(?,?,?,?,?,?)";
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();

            for (int i=0; i<10; i++){
                //分段计时
                long bTime = System.currentTimeMillis();
                //
                while (begin < end){
                    pstm.setString(1, "name"+i);
                    pstm.setString(2, "sex"+i);
                    pstm.setInt(3, 1);
                    pstm.setString(4,"email"+i);
                    pstm.setString(5, "tel"+i);
                    pstm.setString(6, "address"+i);

                    //添加到同一个批处理
                    pstm.addBatch();
                    begin++;
                }
                //执行批处理
                pstm.executeBatch();
                //提交事务
//                conn.commit();
                //边界自增100000
                end += 100000;
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入10W条数据耗时："+(eTime-bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入100W数据共耗时："+(eTime1-bTime1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
