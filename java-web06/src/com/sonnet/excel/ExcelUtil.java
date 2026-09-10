package com.sonnet.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static void main(String[] args) {

    }

    public static <T> List<T> importExcel(InputStream inputStream, Class<T> clazz) {
        List<T> dataList = new ArrayList<>();
        ReadListener<T> readListener = new ReadListener<T>() {
            @Override
            public void invoke(T t, AnalysisContext analysisContext) {
                System.out.println("读取了一行操作：" + t);
                dataList.add(t);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("excel读取一个sheet完毕");
            }
        };
        EasyExcel.read(inputStream, clazz, readListener)
                .sheet() // 指定读取sheet的名称
                .doRead(); // 执行读取操作
        return dataList;
    }

    public static <T> List<T> readExcel(String excelPath, String sheetName, Class<T> clazz) {
        List<T> dataList = new ArrayList<>();
        ReadListener<T> readListener = new ReadListener<T>() {
            @Override
            public void invoke(T t, AnalysisContext analysisContext) {
                System.out.println("读取了一行操作：" + t);
                dataList.add(t);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("excel读取一个sheet完毕");
            }
        };
        // EasyExcel读取Excel时需要指定读取的excel的位置，
        // 还需要指定读取的类型，因为这个类型就指定了excel表头与类型定义的属性的映射关系，
        // 还需要指定行的监听器，因为EasyExcel是按行读取的，这个监听器就是感知一行的读取过程
        EasyExcel.read(excelPath, clazz, readListener)
                .sheet(sheetName) // 指定读取sheet的名称
                .doRead(); // 执行读取操作
        return dataList;
    }

    public static <T> void writeExcel(String excelPath, Class<T> clazz, String sheetName, List<T> dataList) {
        // EasyExcel写excel时必须指定excel存放位置，
        // 还需要指定写的时候excel的表头与属性的对应关系
        EasyExcel.write(excelPath, clazz)
                .sheet(sheetName) //指定写的时候sheet的名称
                .doWrite(dataList); // 执行写数据操作
    }
}
