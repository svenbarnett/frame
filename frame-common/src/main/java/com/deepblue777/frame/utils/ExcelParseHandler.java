package com.deepblue777.frame.utils;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2020-01-09 19:37]
 */
public interface ExcelParseHandler {

    void handle(int sheet, int row, Object[] data);
}
