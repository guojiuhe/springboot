package guojiuhe.controller;

import java.util.ArrayList;
import java.util.List;

public class MainTest{

	public static void main(String[] args) {

		
	}

	private void testList(){
		List<String> list = new ArrayList<String>();
		list.add("aa");
		MainTest test = new MainTest();
		for (int i = 0; i < 5; i++ ){
			list.add(i + "");
			test.modifyList(list);
		}
		//System.out.println(list.size());
		list.forEach(str -> System.out.println(str));
	}


	private void modifyList(List list){
		list.add("bb");
	}
}
