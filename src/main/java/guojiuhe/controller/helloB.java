package guojiuhe.controller;

public class helloB extends helloA {    
	
    public helloB(){        
        System.out.println("helloB");
    }
    
    {System.out.println("I'm B class");}
    static {System.out.println("static B");}        
    public static void main(String [] args){        
        new helloB();
    }
}