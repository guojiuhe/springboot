package guojiuhe.controller;

public class NULL {
	
	public static void haha(){        
	    System.out.print("haha");    
	}
	
	public static void main(String [] args){        
	    ((NULL)null).haha(); // ((NULL)null) 最终还是null 可以调用静态方法， 不可以调用非静态方法
	}
}
