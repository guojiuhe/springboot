package guojiuhe.demo.rocketmq;

public class ModelDemo {
	public ModelDemo() {
		super();
	}
	public ModelDemo(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "ModelDemo [id=" + id + ", name=" + name + "]";
	}
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
