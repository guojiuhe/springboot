package guojiuhe.demo.easyexcel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.EasyExcel;

public class EasyExcelDemo {
	public static void main(String[] args) {
		EasyExcelDemo demo = new EasyExcelDemo();
		demo.excelWrite();
	}
	
	
	public void excelWrite(){
        //1、创建一个文件对象
        File excelFile = new File("D:\\订单表.xlsx");
        //2、判断文件是否存在，不存在则创建一个Excel文件
        if (!excelFile.exists()) {
            try {
                excelFile.createNewFile();//创建一个新的文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //3、指定需要那个class去写。然后写到第一个sheet，名字为模版，然后文件流会自动关闭
        EasyExcel.write(excelFile, ExcelOrder.class).sheet("订单模版").doWrite(data());
        System.out.println("生成excel成功");
    }

    private List<ExcelOrder> data(){
        //创建一个List集合
        List<ExcelOrder> excelOrderList = new ArrayList<ExcelOrder>();

        /*
         *xls版本的Excel最多一次可写0 ...65535行
         * xlsx 版本的Excel最多一次可写0...1048575行
         */
        for (int i=0;i<200;i++){
            ExcelOrder data = new ExcelOrder();
            data.setOrderId("20220224"+(i+1));
            data.setTradeName("商品名称"+i);
            data.setCostPrice(i+5.0);
            data.setSellingPrice(i+10.0);
            excelOrderList.add(data);
        }

        return excelOrderList;//返回list集合
    }
    
    public void excelRead(){
        String fileName = "D:\\订单表.xlsx";//文件路径
        //默认读取第一个sheet
        EasyExcel.read(fileName, ExcelOrder.class,new EasyExcelOrderListener()).sheet().doRead();
    }
}
