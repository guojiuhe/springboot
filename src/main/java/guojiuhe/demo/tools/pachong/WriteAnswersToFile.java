package guojiuhe.demo.tools.pachong;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.Document;

public class WriteAnswersToFile extends Thread {

    private String path;
    private Document dom;//将这个dom放入文件里存储

    /**
     *
     * @param path 文件输出路径
     * @param dom org.dom4j.Document
     */
    public WriteAnswersToFile(String address,Document dom) {
        this.path = address;
        this.dom = dom;
    }
    public WriteAnswersToFile(String path) {
        this.path = path;
    }
    public void run() {
        OutputFormat outFormat = OutputFormat.createPrettyPrint();
        outFormat.setEncoding("UTF-8");
        try {
        	System.out.println("begin write message to file " + path);
            XMLWriter xml = new XMLWriter(new FileWriter(new File(path)),outFormat);
            xml.write(dom);
            xml.close();
	    	System.out.println("write message done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void outFile(String fileName, String message) {
    	File file = new File(path + fileName);
    	System.out.println("begin write message to file " + path);
    	try (FileOutputStream fop = new FileOutputStream(file)) {
	    	if (!file.exists()) {
	    		file.createNewFile();
	    	}
	    	byte[] contentInBytes = message.getBytes();
	    	fop.write(contentInBytes);
	    	fop.flush();
	    	fop.close();
	    	System.out.println("write message done");
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}