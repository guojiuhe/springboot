package guojiuhe.demo.utils;

public class Dictionary{

	public enum EmployeeStatus {
		Active("1"),
		InActive("2");

		private String status;
		EmployeeStatus(String status) {
			this.status = status;
		}

		public String getStr() {
			return status;
		}
	}
}
