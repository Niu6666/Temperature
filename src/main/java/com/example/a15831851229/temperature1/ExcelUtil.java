package com.example.a15831851229.temperature1;

/**
 * Created by 15831851229 on 2021/3/22.
 */
import android.content.Context;
import android.os.StatFs;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import android.os.Environment;
import android.widget.Toast;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
public class ExcelUtil {
    //内存地址
    public static String root = Environment.getExternalStorageDirectory()
            .getPath();

    public static void writeExcel(Context context, List<Man> exportOrder,
                                  String fileName) throws Exception {


        String[] title = {"学生14天健康情况登记表"};
        File file;
        File dir = new File(context.getExternalFilesDir(null).getPath());
        file = new File(dir, fileName + ".xls");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        WritableWorkbook wwb;
        OutputStream os = new FileOutputStream(file);
        wwb = Workbook.createWorkbook(os);
        WritableSheet sheet = wwb.createSheet("学生14天健康情况登记表", 0);
        Label label;
        label = new Label(0, 0, title[0], DaBiaoTi());
        sheet.addCell(label);
        sheet.mergeCells(0, 0, 6, 0);
        label = new Label(0, 1, "单位名称：", BiaoTi());
        sheet.addCell(label);
        sheet.mergeCells(1, 1, 3, 1);
        label = new Label(4, 1, "填表日期：", BiaoTi());
        sheet.addCell(label);
        sheet.mergeCells(5, 1, 6, 1);
        label = new Label(0, 2, "姓名", yiban());
        sheet.addCell(label);
        sheet.mergeCells(1, 2, 3, 2);
        label = new Label(4, 2, "学号", yiban());
        sheet.addCell(label);
        sheet.mergeCells(5, 2, 6, 2);
        label = new Label(0, 3, "目前健康状况", yiban());
        sheet.addCell(label);
        label = new Label(1, 3, "健康", yiban());
        sheet.addCell(label);
        sheet.mergeCells(1, 3, 3, 3);
        label = new Label(4, 3, "手机号", yiban());
        sheet.addCell(label);
        sheet.mergeCells(5, 3, 6, 3);
        label = new Label(0, 4, "每日体温、健康状况监测（周期14天）", DaBiaoTi2());
        sheet.addCell(label);
        sheet.mergeCells(0, 4, 6, 4);
        label = new Label(0, 5, "日期", yiban());
        sheet.addCell(label);
        label = new Label(1, 5, "每日体温℃", yiban());
        sheet.addCell(label);
        label = new Label(2, 5, "健康状况", yiban());
        sheet.addCell(label);
        label = new Label(3, 5, "当日所在地", yiban());
        sheet.addCell(label);
        sheet.mergeCells(3, 5, 4, 5);
        label = new Label(5, 5, "备注", yiban());
        sheet.addCell(label);
        sheet.mergeCells(5, 5, 6, 5);
        sheet.setColumnView(0, 11);//0lie
        sheet.setColumnView(1, 11);//1lie
        sheet.setColumnView(2, 11);//2lei
        sheet.setColumnView(3, 11);//3lie
        sheet.setColumnView(4, 11);//4lie
        sheet.setColumnView(5, 11);//5lie
        sheet.setColumnView(6, 11);//6lie
        sheet.setRowView(0, 800);//0hang
        sheet.setRowView(1, 500);//1hang
        sheet.setRowView(2, 600);//2hang
        sheet.setRowView(3, 1200);//3hang
        sheet.setRowView(4, 800);//4hang
        sheet.setRowView(5, 800);//5hang
        sheet.setRowView(20, 1800);//20hang
        sheet.setRowView(21, 700);//21hang
        label = new Label(1, 1, "信1905-1班", yiban());
        sheet.addCell(label);
        Date date = new Date();
        SimpleDateFormat formatter1 = new SimpleDateFormat("MM月dd日");
        String NowTime = formatter1.format(date);
        label = new Label(5, 1, NowTime, yiban());
        sheet.addCell(label);
        label = new Label(1, 2, "牛振鹏", yiban());
        sheet.addCell(label);
        label = new Label(5, 2, "20194007", yiban());
        sheet.addCell(label);
        label = new Label(5, 3, "15831851229", yiban());
        sheet.addCell(label);
        /*
        label=new Label(1,6,exportOrder.get(0).getWendu(),yiban());
        sheet.addCell(label);
        */


        for (int i = 0; i < 14; i++) {
            label = new Label(0, i + 6, "3月" + (i + 5) + "日", yiban());
            sheet.addCell(label);
            sheet.setRowView(6 + i, 800);

            if (exportOrder.size() > i) {
                label = new Label(1, i + 6, exportOrder.get(i).getTem(), yiban());
                sheet.addCell(label);
                if (Double.parseDouble(exportOrder.get(i).getTem()) >= 34.0 && Double.parseDouble(exportOrder.get(i).getTem()) <= 37.0) {
                    label = new Label(2, i + 6, "健康", yiban());
                    sheet.addCell(label);
                } else {
                    label = new Label(2, i + 6, "异常", yiban());
                    sheet.addCell(label);
                }
                label = new Label(3, i + 6, exportOrder.get(i).getArea(), yiban());
                sheet.addCell(label);
                label = new Label(5, i + 6, exportOrder.get(i).getSpecial(), yiban());
                sheet.addCell(label);
            }

            sheet.mergeCells(3, 6 + i, 4, 6 + i);
            sheet.mergeCells(5, 6 + i, 6, 6 + i);
        }


        label = new Label(0, 20, "本人承诺：自觉履行疫情防控责任和义务，保证以上填报信息全部属实，如有隐瞒，自愿承担相应法律后果。", yiban());
        sheet.addCell(label);
        sheet.mergeCells(0, 20, 6, 20);
        label = new Label(0, 21, "本人签字：", yiban());
        sheet.addCell(label);
        sheet.mergeCells(0, 21, 1, 21);
        sheet.mergeCells(2, 21, 3, 21);
        label = new Label(4, 21, "签字日期：", yiban());
        sheet.addCell(label);
        sheet.mergeCells(5, 21, 6, 21);


        wwb.write();
        // 关闭文件
        wwb.close();
        Toast.makeText(context, "写入成功", Toast.LENGTH_LONG).show();


    }

    public static WritableCellFormat DaBiaoTi() {
        WritableFont font = new WritableFont(WritableFont.createFont("黑体"), 18,
                WritableFont.BOLD, false);// 定义字体

        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            // format.setBorder(Border.ALL, BorderLineStyle.THIN,
            // Colour.BLACK);// 黑色边框
            // format.setBackground(Colour.YELLOW);// 黄色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        //sheet.mergeCells(0,0,6,0);
        return format;
    }

    public static WritableCellFormat DaBiaoTi2() {
        WritableFont font = new WritableFont(WritableFont.createFont("黑体"), 18,
                WritableFont.BOLD, false);// 定义字体

        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
            // format.setBackground(Colour.YELLOW);// 黄色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        //sheet.mergeCells(0,0,6,0);
        return format;
    }

    public static WritableCellFormat BiaoTi() {
        WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 12,
                WritableFont.BOLD, false);// 定义字体

        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            // format.setBorder(Border.ALL, BorderLineStyle.THIN,
            // Colour.BLACK);// 黑色边框
            // format.setBackground(Colour.YELLOW);// 黄色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        //sheet.mergeCells(0,0,6,0);
        return format;
    }

    public static WritableCellFormat yiban() {
        WritableFont font = new WritableFont(WritableFont.createFont("楷体"), 12,
                WritableFont.NO_BOLD, false);// 定义字体

        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
            format.setWrap(true);
            // format.setBackground(Colour.YELLOW);// 黄色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        //sheet.mergeCells(0,0,6,0);
        return format;
    }
}
//        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&&getAvailableStorage()>1000000) {
//            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
//            return;
//        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        String[] title={"学生14天健康情况登记表"};
//        //String[] title = { "姓名", "体温", "时间", "地点" };
//        File file;
//        File dir = new File(context.getExternalFilesDir(null).getPath());
//        file = new File(dir, fileName + ".xls");
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        // 创建Excel工作表
//        WritableWorkbook wwb;
//        OutputStream os = new FileOutputStream(file);
//        wwb = Workbook.createWorkbook(os);
//        // 添加第一个工作表并设置第一个Sheet的名字
//        WritableSheet sheet = wwb.createSheet("订单", 0);
//        Label label;
//        for (int i = 0; i < title.length; i++) {
//            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
//            // 在Label对象的子对象中指明单元格的位置和内容
//            label = new Label(i, 0, title[i], getHeader());
//            // 将定义好的单元格添加到工作表中
//            sheet.addCell(label);
//        }
//
//        for (int i = 0; i < exportOrder.size(); i++) {
//            Man order = exportOrder.get(i);
//
//            Label orderNum = new Label(0, i + 1, order.name);
//            Label restaurant = new Label(1, i + 1, order.tem);
//            Label nameLabel = new Label(2,i+1,order.date);
//            Label address = new Label(3, i + 1, order.area);
//
//            sheet.addCell(orderNum);
//            sheet.addCell(restaurant);
//            sheet.addCell(nameLabel);
//            sheet.addCell(address);
//            Toast.makeText(context, "写入成功", Toast.LENGTH_LONG).show();
//
//        }
//        // 写入数据
//        wwb.write();
//        // 关闭文件
//        wwb.close();
//    }
//
//    public static WritableCellFormat getHeader() {
//        WritableFont font = new WritableFont(WritableFont.TIMES, 10,
//                WritableFont.BOLD);// 定义字体
//        try {
//            font.setColour(Colour.BLUE);// 蓝色字体
//        } catch (WriteException e1) {
//            e1.printStackTrace();
//        }
//        WritableCellFormat format = new WritableCellFormat(font);
//        try {
//            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
//            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
//            // format.setBorder(Border.ALL, BorderLineStyle.THIN,
//            // Colour.BLACK);// 黑色边框
//            // format.setBackground(Colour.YELLOW);// 黄色背景
//        } catch (WriteException e) {
//            e.printStackTrace();
//        }
//        return format;
//    }
//
//    /** 获取SD可用容量 */
//    private static long getAvailableStorage() {
//
//        StatFs statFs = new StatFs(root);
//        long blockSize = statFs.getBlockSize();
//        long availableBlocks = statFs.getAvailableBlocks();
//        long availableSize = blockSize * availableBlocks;
//        // Formatter.formatFileSize(context, availableSize);
//        return availableSize;
//    }
//}