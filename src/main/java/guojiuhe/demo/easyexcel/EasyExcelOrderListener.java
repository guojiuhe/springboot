package guojiuhe.demo.easyexcel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class EasyExcelOrderListener extends AnalysisEventListener<ExcelOrder>{
	
	Logger log = LoggerFactory.getLogger(EasyExcelOrderListener.class);
	/**
     * 此方法每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(ExcelOrder data, AnalysisContext context) {
        log.info("解析到一条数据："+data);
    }

    /**
     * 所有数据解析完成都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！！！");
    }
}
