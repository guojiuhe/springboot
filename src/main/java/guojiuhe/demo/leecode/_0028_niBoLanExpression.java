package guojiuhe.demo.leecode;

import java.util.Deque;
import java.util.LinkedList;

public class _0028_niBoLanExpression {
	/*
	 * 逆波兰表达式求值
	 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
	 * 请你计算该表达式。返回一个表示表达式值的整数。
	 * 注意：有效的算符为 '+'、'-'、'*' 和 '/' 。每个操作数（运算对象）都可以是一个整数或者另一个表达式。
	 * 两个整数之间的除法总是 向零截断 。
	 * 表达式中不含除零运算。
	 * 输入是一个根据逆波兰表示法表示的算术表达式。
	 * 答案及所有中间计算结果可以用 32 位 整数表示。
	 *  示例 1：
		输入：tokens = ["2","1","+","3","*"]
		输出：9
		解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
		
		示例 2：
		输入：tokens = ["4","13","5","/","+"]
		输出：6
		解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
		
		示例 3：
		输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
		输出：22
		解释：该算式转化为常见的中缀算术表达式为：
		  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
		= ((10 * (6 / (12 * -11))) + 17) + 5
		= ((10 * (6 / -132)) + 17) + 5
		= ((10 * 0) + 17) + 5
		= (0 + 17) + 5
		= 17 + 5
		= 22
	 */
	
	/*
	 * 栈 逻辑比较好理解
	 * 运算符总是计算前面的2位
	 */
	  public int evalRPN(String[] tokens) {
	        Deque<Integer> stack = new LinkedList<Integer>();
	        int n = tokens.length;
	        for (int i = 0; i < n; i++) {
	            String token = tokens[i];
	            if (isNumber(token)) {
	                stack.push(Integer.parseInt(token));
	            } else {
	                int num2 = stack.pop();
	                int num1 = stack.pop();
	                switch (token) {
	                    case "+":
	                        stack.push(num1 + num2);
	                        break;
	                    case "-":
	                        stack.push(num1 - num2);
	                        break;
	                    case "*":
	                        stack.push(num1 * num2);
	                        break;
	                    case "/":
	                        stack.push(num1 / num2);
	                        break;
	                    default:
	                }
	            }
	        }
	        return stack.pop();
	    }

	    public boolean isNumber(String token) {
	        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
	    }
	    
	    public int evalRPN_1(String[] tokens) {
	        int n = tokens.length;
	        int[] stack = new int[(n + 1) / 2]; // 运算符比数字多一位
	        int index = -1;
	        for (int i = 0; i < n; i++) {
	            String token = tokens[i];
	            switch (token) {
	                case "+":
	                    index--;
	                    stack[index] += stack[index + 1];
	                    break;
	                case "-":
	                    index--;
	                    stack[index] -= stack[index + 1];
	                    break;
	                case "*":
	                    index--;
	                    stack[index] *= stack[index + 1];
	                    break;
	                case "/":
	                    index--;
	                    stack[index] /= stack[index + 1];
	                    break;
	                default:
	                    index++;
	                    stack[index] = Integer.parseInt(token);
	            }
	        }
	        return stack[index];
	    }
}
