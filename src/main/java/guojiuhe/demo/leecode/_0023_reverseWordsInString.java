package guojiuhe.demo.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _0023_reverseWordsInString {
	/*
	 * 反转字符串中的单词
	 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
	 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
	 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
	 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。
	 * 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
	 */
	public String reverseWords(String s) {
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        //List<String> wordList = Arrays.asList(s.split("\\s+")); // \s+ 匹配空格 如果只有1个空格，可以s.split(" ")
        
        // 如果写不出正则，可以遍历把多余空格剔除
        List<String> wordList = new ArrayList<String>();
        char[] arr = s.toCharArray();
    	StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
        	// 已经trim, 首尾必不存在空格
        	if (i > 0 && arr[i] == ' ' && arr[i-1] == ' ') {
        		
        	}
        	else if (i > 0 && arr[i] == ' ' && arr[i-1] != ' ') {
        		wordList.add(sb.toString());
        		sb = new StringBuilder();
        	}
        	else {
        		sb.append(arr[i]);
        	}
        }
		wordList.add(sb.toString());
        //Collections.reverse(wordList);
        //return String.join(" ", wordList);
		// 不用 Collections，自己反转 leecode提交比Collections.reverse更快
		sb = new StringBuilder(); 
		for (int i = wordList.size() - 1; i >= 0; i --) {
			if (i > 0) {
				sb.append(wordList.get(i)).append(" ");
			}
			else {
				sb.append(wordList.get(i));
			}
		}
		return sb.toString();
    }
}
