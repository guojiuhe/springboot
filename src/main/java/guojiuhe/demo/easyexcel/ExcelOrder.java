package guojiuhe.demo.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExcelOrder {
	// @ExcelProperty：指定当前字段对应excel中的那一列。
    @ExcelProperty("订单编号")
    private String orderId;//订单编号

    @ExcelProperty("商品名称")
    private String tradeName;//商品名称

	@ExcelProperty("成本价")
    private Double costPrice;//成本价

    @ExcelProperty("销售价")
    private Double sellingPrice;//销售价

    public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
}
