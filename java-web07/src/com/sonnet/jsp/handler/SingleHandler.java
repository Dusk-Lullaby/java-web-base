// 声明当前类所在的包
package com.sonnet.jsp.handler;

// 导入 Apache Commons BeanUtils 工具类
import org.apache.commons.beanutils.BeanUtils;

// 导入数据库查询结果集接口
import java.sql.ResultSet;
// 导入结果集元数据接口
import java.sql.ResultSetMetaData;
// 导入 SQL 异常类
import java.sql.SQLException;
// 导入 HashMap 集合类
import java.util.HashMap;
// 导入 Map 集合接口
import java.util.Map;

// 定义用于处理单条查询结果的结果集处理器
public class SingleHandler<T> implements ResultHandler<T> {

    // 保存需要将查询结果转换成的 JavaBean 类型
    private final Class<T> clazz;

    /**
     * 创建单条结果处理器。
     */
    // 接收需要转换成的 JavaBean 类型
    public SingleHandler(Class<T> clazz) {
        // 将传入的类型保存到成员变量中
        this.clazz = clazz;
    }

    /**
     * 将一条查询结果封装成 JavaBean。
     */
    // 实现 ResultHandler 接口中处理结果集的方法
    @Override
    public T handle(ResultSet resultSet) throws SQLException {
        // 保存最终封装完成的 JavaBean 对象，没有查询结果时保持为 null
        T result = null;
        // 记录结果集中已经读取的数据条数
        int count = 0;

        // 让结果集游标向后移动，并判断当前是否还有一条数据
        while (resultSet.next()) {
            // 每成功读取一条数据，结果数量加一
            count++;
            // 单条结果处理器不允许查询出两条或更多数据
            if (count > 1) {
                // 查询结果超过一条时抛出异常，提醒调用者检查 SQL 条件
                throw new RuntimeException("查询结果存在多条数据：" + count);
            }

            // 捕获创建对象和设置属性时可能出现的异常
            try {
                // 调用无参构造方法创建一个 JavaBean 对象
                T bean = clazz.getDeclaredConstructor().newInstance();
                // 创建 Map，用于保存“列名和列值”的对应关系
                Map<String, Object> values = new HashMap<>();
                // 获取结果集的元数据，通过元数据可以得到列的数量和名称
                ResultSetMetaData metaData = resultSet.getMetaData();
                // 获取本次查询结果的列数
                int columnCount = metaData.getColumnCount();

                // JDBC 的列下标从 1 开始，因此这里从 1 遍历到列数
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    // 获取当前列的标签，SQL 使用别名时得到的是别名
                    String columnLabel = metaData.getColumnLabel(columnIndex);
                    // 根据当前列的下标获取这一列的数据
                    Object columnValue = resultSet.getObject(columnIndex);
                    // 将列标签作为键、列值作为值存入 Map
                    values.put(columnLabel, columnValue);
                }

                // 根据 Map 中的属性名和属性值，为 JavaBean 调用对应的 setter 方法
                BeanUtils.populate(bean, values);
                // 保存已经封装完成的 JavaBean 对象
                result = bean;
            // 捕获反射创建对象或 BeanUtils 设置属性时产生的异常
            } catch (Exception exception) {
                // 将受检异常包装成运行时异常并继续抛出
                throw new RuntimeException(exception);
            }
        }

        // 返回封装完成的对象；查询不到数据时返回 null
        return result;
    }
}
