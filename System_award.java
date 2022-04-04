package com.java;
import java.sql.*;
import java.util.ResourceBundle;
/*为了提高老师在评分效率设计一个java小程序降低老师评分的工作量
   使用连接来调佣数据库中的信息自动进行打分
   建立数据库的连接获取数据库中的学生信息
   然后使用设计的程序进行自动打分并进行输出*/
public class System_award {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*使用属性文件把ip地址和用户名密码进行封装以
        * 保证域名和ip地址的安全性*/
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
//        使用driver获取com.mysql.jc.jddbc.Driver
        String driver = bundle.getString("driver");
//        获取url，user，password
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection conn = null;
        Statement stmt = null;
        ResultSet res =null;
        try {
//            1.注册驱动
            Class.forName(driver);
//            2.获取连接
            conn = DriverManager.getConnection(url, user, password);
//            3.创建数据库操作对象
            stmt = conn.createStatement();
//            4.执行sql语句对数据进行修改
//            String sql1="update student set Name='小玉' where ID=1012";
//            int i = stmt.executeUpdate(sql1);
//            System.out.println(i ==1 ? "修改成功" : "修改失败");
//            对数据库中的值进行添加
//            String sql2="insert into student values(?,'?','?',?)";
//            进行sql语句查询数据库
            String sql = "select id,name,score from student order by score desc";
            res = stmt.executeQuery(sql);
            while (res.next()) {
                int score = res.getInt("score");
                int id = res.getInt("ID");
                String Name = res.getString("Name");
//                String sex=res.getString("sex");
//                String dept = res.getString("dept");
                System.out.println("学号:" + id);
                System.out.println("姓名:" + Name);
//                System.out.println("sex:"+sex);
//                System.out.println("院系:"+dept);
                System.out.println("成绩:" + score);
                if (score >= 90) {
                    System.out.println("一等奖");
                } else if (score >= 80) {
                    System.out.println("二等奖");
                } else if (score >= 70) {
                    System.out.println("三等奖");
                } else {
                    System.out.println("参与奖");
                }
                System.out.println("\n");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(res!=null){
                try {
                    res.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}

