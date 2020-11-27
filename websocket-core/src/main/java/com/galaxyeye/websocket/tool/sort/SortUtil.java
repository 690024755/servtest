package com.galaxyeye.websocket.tool.sort; /*
 * Description:com.galaxyeye.websocket.tool.sort
 * @Date Create on 17:01
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2020/6/4日galaxyeye All Rights Reserved.
 */

import com.galaxyeye.websocket.tool.date.DateTool;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;

@Slf4j
public class SortUtil {


    public static Boolean IsSortByLong(List<Long> p, Integer m) {
        int i, j, k = 1;
        for (i = 0; i < m; i++)
            for (j = i + 1; j < m; j++)
                if (p.get(i) < p.get(j)) {
                    k = 0;
                    break;
                }
        if (k == 0) return false;
        else return true;
    }



    public static Boolean IsSortByInteger(List<Integer> p, Integer m) {
        int i, j, k = 1;
        for (i = 0; i < m; i++)
            for (j = i + 1; j < m; j++)
                if (p.get(i) < p.get(j)) {
                    k = 0;
                    break;
                }
        if (k == 0) return false;
        else return true;
    }


    public static Boolean IsSortByDate(List<Date> p, Integer m) {
        int i, j, k = 1;
        for (i = 0; i < m; i++)
            for (j = i + 1; j < m; j++)
                if (p.get(i).getTime() < p.get(j).getTime()) {
                    k = 0;
                    break;
                }
        if (k == 0) return false;
        else return true;
    }



    public static Boolean IsSortByDateStr(List<String> p, Integer m) {
        int i, j, k = 1;
        for (i = 0; i < m; i++)
            for (j = i + 1; j < m; j++)
                if (DateTool.parseDateStr(p.get(i),DateTool.DATE_FMT_5).getTime() < DateTool.parseDateStr(p.get(j),DateTool.DATE_FMT_5).getTime()) {
                    k = 0;
                    break;
                }
        if (k == 0) return false;
        else return true;
    }


}
