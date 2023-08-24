package guojiuhe.demo.tools;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreditTool {

	public static void main(String[] args) {
		/*
		 * 开始还贷时间 2018-09-01 第一月还2300 以后每月5048 
		 * 2020-07-21转换为LRP + 59点
		 * LPR 开始执行时间 2021-01-01
		 * 2023.01：1年期3.65%，5年期以上4.30% 
		   2022.01：1年期3.70%，5年期以上4.60%
		   2021.01：1年期3.85%，5年期以上4.65%
		   2020.01：1年期4.15%，5年期以上4.80%  基准 4.8% + 59 = 5.39%
		   2019.08：1年期4.25%，5年期以上4.85%
		 */
		double totalInvest = 900000;
		double yearRate = 5.39 / 100;
		int totalMonth = 360;
		//System.out.println("等额本息每月还款:" + getPerMonthPrincipalInterest(totalInvest, yearRate, totalMonth));
		//System.out.println("每月还款利息");
		//Map<Integer, Double> interestMap = getPerMonthInterest(totalInvest, yearRate, totalMonth);
		//System.out.println("每月还款本金");
		//Map<Integer, Double> principalMap = getPerMonthPrincipal(totalInvest, yearRate, totalMonth);
		//System.out.println("总利息:" + getInterestCount(totalInvest, yearRate, totalMonth));
		
		// LPR 4.8% + 59个点 = 5.39% 2023LPR基础为4.3% 我的利率为4.89%
		System.out.println("转换LPR后"); // 以下均为估算(下面的未还本金&利息是2月的，实际应该算1月的, 或者根据interestMap&principalMap精确计算已还本金利息)
		totalMonth = totalMonth - 52; // 截至2023-01-01 还款52个月
		yearRate = 4.89 / 100;
		double 未还本金 = 835804.12;
		double 未还利息 = 2384.13;
		System.out.println("等额本息每月还款:" + getPerMonthPrincipalInterest(未还本金 + 未还利息, yearRate, totalMonth));
		
		// 20230301(还款2月) 提前还贷
		totalMonth = totalMonth -1;
		未还本金 = 835804.12;
		未还利息 = 2384.13;
		System.out.println("准备提前还贷 5 万");
		未还本金 = 未还本金 - 50000;
		System.out.println("等额本息每月还款:" + getPerMonthPrincipalInterest(未还本金 + 未还利息, yearRate, totalMonth));
		
		System.out.println("准备提前还贷 10 万");
		未还本金 = 未还本金 - 50000;
		System.out.println("等额本息每月还款:" + getPerMonthPrincipalInterest(未还本金 + 未还利息, yearRate, totalMonth));
		
		System.out.println("准备提前还贷 20 万");
		未还本金 = 未还本金 - 100000;
		System.out.println("等额本息每月还款:" + getPerMonthPrincipalInterest(未还本金 + 未还利息, yearRate, totalMonth));
		
		System.out.println("准备提前还贷 40 万");
		未还本金 = 未还本金 - 200000;
		System.out.println("等额本息每月还款:" + getPerMonthPrincipalInterest(未还本金 + 未还利息, yearRate, totalMonth));
	}

	/**
     * 每月偿还本金和利息
     * <p>
     * 公式：每月偿还本息=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
     *
     * @param invest     总借款额（贷款本金,单位分）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还本金和利息(入1 单位分)
     */
    public static double getPerMonthPrincipalInterest(double invest, double yearRate, int totalMonth) {
        double monthRate = yearRate / 12;
        double perMonthPrincipalInterest = invest * (monthRate * Math.pow(1 + monthRate, totalMonth))/(Math.pow(1 + monthRate, totalMonth) - 1);
        return new BigDecimal(perMonthPrincipalInterest).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
 
    /**
     * 等额本息的每月偿还利息
     * <p>
     * 公式：每月偿还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
     *
     * @param invest     总借款额（贷款本金,分）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还利息(入1 单位分)
     */
    public static Map<Integer, Double> getPerMonthInterest(double invest, double yearRate, int totalMonth) {
        Map<Integer, Double> map = new HashMap<>();
        double monthRate = yearRate / 12;
        double monthInterest;
        for (int i = 1; i < totalMonth + 1; i++) {
            double multiply = invest * monthRate;
            double sub = Math.pow(1 + monthRate, totalMonth) - Math.pow(1 + monthRate, i - 1);
            monthInterest = multiply * sub/(Math.pow(1 + monthRate, totalMonth) - 1);
            double interest = new BigDecimal(monthInterest).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            map.put(i, interest);
            //System.out.println("月数:" + i + "  ->  " + interest);
        }
        return map;
    }
 
    /**
     * 等额本息的每月偿还本金（月还本息-月还利息）
     *
     * @param invest     总借款额（贷款本金,分）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还本金(取整舍 单位分)
     */
    public static Map<Integer, Double> getPerMonthPrincipal(double invest, double yearRate, int totalMonth) {
        double monthRate = yearRate / 12;
        double monthIncome = invest * monthRate * Math.pow(1 + monthRate, totalMonth)
                /(Math.pow(1 + monthRate, totalMonth) - 1);
        double perMonthPrincipalInterest = new BigDecimal(monthIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
 
        Map<Integer, Double> mapPrincipal = new HashMap<>();
        double monthInterest;
        for (int i = 1; i < totalMonth + 1; i++) {
            Double multiply = invest * monthRate;
            double sub = (Math.pow(1 + monthRate, totalMonth)) - (Math.pow(1 + monthRate, i - 1));
            monthInterest = multiply* sub/(Math.pow(1 + monthRate, totalMonth) - 1);
            double monthInterestL = new BigDecimal(monthInterest).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
           // System.out.println("===perMonthPrincipalInterest===="+perMonthPrincipalInterest);
           // System.out.println("===monthInterestL===="+monthInterestL);
           // System.out.println("===perMonthPrincipalInterest-monthInterestL===="+(perMonthPrincipalInterest-monthInterestL));
           // System.out.println("===perMonthPrincipalInterest-monthInterestL===="+ new BigDecimal(perMonthPrincipalInterest).subtract(new BigDecimal(monthInterestL)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
           // mapPrincipal.put(i, perMonthPrincipalInterest-monthInterestL);
            mapPrincipal.put(i, new BigDecimal(perMonthPrincipalInterest).subtract(new BigDecimal(monthInterestL)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            //System.out.println("月数:" + i + "  ->  " + new BigDecimal(perMonthPrincipalInterest).subtract(new BigDecimal(monthInterestL)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return mapPrincipal;
    }
 
    /**
     * 等额本息的总利息
     *
     * @param invest     总借款额（贷款本金）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 总利息 (单位分)
     */
    public static double getInterestCount(double invest, double yearRate, int totalMonth) {
    	double count = 0;
        Map<Integer, Double> mapInterest = getPerMonthInterest(invest, yearRate, totalMonth);
 
        for (Map.Entry<Integer, Double> entry : mapInterest.entrySet()) {
            count = new BigDecimal(count).add(new BigDecimal(entry.getValue())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return count;
    }
}
