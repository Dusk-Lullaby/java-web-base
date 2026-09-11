// 声明当前类所在的包
package com.sonnet.jsp.jdbc;

// 导入 Druid 数据源类
import com.alibaba.druid.pool.DruidDataSource;
// 导入自定义的结果集处理器接口
import com.sonnet.jsp.handler.ResultHandler;

// 导入数据库连接接口
import java.sql.Connection;
// 导入预编译 SQL 语句接口
import java.sql.PreparedStatement;
// 导入数据库查询结果集接口
import java.sql.ResultSet;
// 导入 SQL 异常类
import java.sql.SQLException;
// 导入属性配置集合类
import java.util.Properties;

// 定义 JDBC 工具类
public class JdbcUtil {

    // 创建整个应用程序共享的 Druid 数据源对象
    private static final DruidDataSource dataSource = new DruidDataSource();

    /**
     * 初始化数据源。
     *
     * @param properties 数据库和连接池配置
     */
    // 定义初始化数据源的静态方法
    public static void initDataSource(Properties properties) {
        // 将配置文件中的属性设置到 Druid 数据源中
        dataSource.configFromProperties(properties);
    }

    /**
     * 关闭数据源。
     */
    // 定义关闭数据源的静态方法
    public static void destroyDataSource() {
        // 关闭 Druid 数据源并释放连接池资源
        dataSource.close();
    }

    /**
     * 执行通用查询。
     *
     * @param sql 查询 SQL
     * @param handler 结果集处理器
     * @param params SQL 参数
     * @return 查询结果
     * @param <T> 查询结果类型
     */
    // 定义一个可以返回任意类型查询结果的静态泛型方法
    public static <T> T query(String sql, ResultHandler<T> handler, Object...params) {
        // 开始捕获 JDBC 操作可能出现的 SQL 异常
        try {
            // 从 Druid 连接池中获取一个数据库连接
            Connection connection = dataSource.getConnection();
            // 根据传入的 SQL 创建预编译语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // 判断调用者是否传入了 SQL 占位符参数
            if (params != null && params.length > 0) {
                // 遍历所有 SQL 参数
                for (int i = 0; i < params.length; i++) {
                    // 将参数依次设置到 SQL 的问号占位符中，JDBC 参数下标从 1 开始
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            // 获取预编译语句执行后产生的查询结果集
            ResultSet resultSet = preparedStatement.getResultSet();
            // 使用调用者传入的处理器将结果集转换为指定类型
            T t = handler.handle(resultSet);
            // 关闭结果集资源
            resultSet.close();;
            // 关闭预编译语句资源
            preparedStatement.close();
            // 关闭连接，将连接归还给 Druid 连接池
            connection.close();
        // 捕获数据库操作过程中出现的 SQL 异常
        } catch (SQLException e) {
            // 将受检的 SQL 异常包装成运行时异常并继续抛出
            throw new RuntimeException(e);
        }
        // 当前方法没有返回上面处理得到的变量 t，因此暂时返回 null
        return null;
    }
}
