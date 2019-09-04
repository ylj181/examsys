package com.qfedu.examsys.utils;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class secToTime {
    public static String secToTime(int time) {
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String dataTime = "";
        int hour = time / 3600;dataTime += decimalFormat.format(hour) + ":";
        int minutes = (time - hour * 3600) / 60;
        dataTime += decimalFormat.format(minutes) + ":";
        int second = time % 60;
        dataTime += decimalFormat.format(second);

        //00:00:00
        //01:50
        String timms =dataTime.substring(0,dataTime.length()-3);

        return timms;
    }

}
