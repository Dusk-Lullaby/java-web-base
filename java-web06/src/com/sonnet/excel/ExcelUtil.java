package com.sonnet.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

// 每个sheet中写的最大数据
private static final int MAX_COUNT_PER_SHEET = 5000;

    public static <T> List<T> readExcel(InputStream inputStream, Class<T> clazz) {
        List<T> dataList = new ArrayList<>();
        ReadListener<T> readListener = new ReadListener<T>() {
            @Override
            public void invoke(T t, AnalysisContext analysisContext) {
                dataList.add(t);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        };
        EasyExcel.read(inputStream, clazz, readListener)
                .doReadAll();
        return dataList;
    }

    public static <T> void writerExcel(OutputStream outputStream, Class<T> clazz, String sheetName, List<T> dataList) {
        // EasyExcel写excel时必须指定excel存放位置，
        // 还需要指定写的时候excel的表头与属性的对应关系
        ExcelWriter writer = EasyExcel.write(outputStream, clazz).build();
        // 数据总条数
        int size = dataList.size();
        // 计算sheet数量
        // 例如：10000条数据，每个Sheet写5000条，则需要2个Sheet
        int sheetCount = (size + MAX_COUNT_PER_SHEET - 1) / MAX_COUNT_PER_SHEET;
        if (sheetCount == 0) {
            sheetCount++;
        }
        for (int i = 0; i < sheetCount; i++) {
            int start = i * MAX_COUNT_PER_SHEET;
            int end = (i + 1) *  MAX_COUNT_PER_SHEET;
            end = Math.min(end, size);
            List<T> sheetData = dataList.subList(start, end);
            WriteSheet writeSheet = new WriteSheet();
            writeSheet.setSheetNo(i);
            writeSheet.setSheetName(sheetName + (i + 1));
            writer.write(sheetData, writeSheet);
        }
        writer.finish();
    }
}
