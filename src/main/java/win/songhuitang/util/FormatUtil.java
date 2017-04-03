package win.songhuitang.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by simon.song on 2017/4/2.
 */
public class FormatUtil {

    public static Date StringToDate(String str){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(str!=null&&(!str.trim().equals(""))){
            try {
                date = sdf.parse(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static String dateToString(Date date){
        String s = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(date!=null){
            try {
                s = sdf.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    /**
     * 判断时间大于或者等于当天
     *
     * @param day
     *            时间
     * @return
     */
    public static boolean isGreaterOrEquToday(Date day) {
        java.util.Calendar tc = Calendar.getInstance();
        tc.setTime(day);
        Calendar td = Calendar.getInstance();
        td.set(Calendar.HOUR_OF_DAY, 0);
        td.set(Calendar.MINUTE, 0);
        td.set(Calendar.SECOND, 0);
        td.set(Calendar.MILLISECOND, 0);
        return tc.after(td) || tc.equals(td);
    }
    /**
     * 判断时间小于或者等于当天
     *
     * @param day
     *            时间
     * @return
     */
    public static boolean isLessOrEquToday(Date day) {
        java.util.Calendar tc = Calendar.getInstance();
        tc.setTime(day);
        Calendar td = Calendar.getInstance();
        td.set(Calendar.HOUR_OF_DAY, 0);
        td.set(Calendar.MINUTE, 0);
        td.set(Calendar.SECOND, 0);
        td.set(Calendar.MILLISECOND, 0);
        return tc.before(td) || tc.equals(td);
    }

    /**
     * @Title: isToday
     * @Description: 判断day日期是否是今天
     * @param day
     * @return
     * @throws
     */
    public static boolean isToday(Date day) {
        java.util.Calendar tc = Calendar.getInstance();
        tc.setTime(day);
        Calendar td = Calendar.getInstance();
        return tc.get(Calendar.YEAR) == td.get(Calendar.YEAR)
                && tc.get(Calendar.MONTH) == td.get(Calendar.MONTH)
                && tc.get(Calendar.DAY_OF_MONTH) == td
                .get(Calendar.DAY_OF_MONTH);
    }
    /**
     * @Title: isToday
     * @Description: 判断是否是当月
     * @param date
     * @return
     * @throws
     */
    public static boolean isCurrentMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Calendar now = Calendar.getInstance();
        boolean bl = c.get(Calendar.YEAR) == now.get(Calendar.YEAR)&& c.get(Calendar.MONTH) == now.get(Calendar.MONTH);
        return bl;
    }

    /**
     * 获取前N年到当前年
     * @param num 多少年
     * @return
     */
    public static String getYearStr(int num,int endYear) {
        //Calendar now = Calendar.getInstance();
        String yearStr = "";
        for (int i = 0; i < num; i++) {
            yearStr+=(endYear-i)+",";
        }
        if(yearStr.length()>1){
            yearStr = yearStr.substring(0,yearStr.length()-1);
        }
        return yearStr;
    }


    /**
     * 过滤空值
     * @param in
     * @return
     */
    public static String filterEmpty(Integer in){
        return in==null?"-":in.toString();
    }

    public static String filterEmpty(Double in){
        return in==null||in<=0?"-":in.toString();//百分比<0的也用“-”表示
    }

    public static String filterEmpty(String in){
        return in==null?"-":in.toString();
    }

    public static String filterEmpty(Long in){
        return in==null?"-":in.toString();
    }

    //字符串转整型
    public static Integer stringToInt(String str){
        Integer result = null;
        try {
            if(str!=null&&(!str.trim().equals(""))){
                result = Integer.parseInt(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
