package com.sonnet.jsp.handler;

import org.apache.commons.beanutils.BeanUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiResultHandler<T> implements  ResultHandler<List<T>>{

    private Class<T> clazz;

    public MultiResultHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<T> handle(ResultSet resultSet) throws SQLException {
        List<T> dataList = new ArrayList<>();
        while (resultSet.next()) {
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
                // 使用工具类将对我们的对象的属性值进行注入
                BeanUtils.populate(bean, values);
                dataList.add(bean);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dataList;
    }
}
