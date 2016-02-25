package test;

public class Test {

	public static void main(String[] args) {
		String success_details = "C145612681796045^18682371425^罗登^0.01^S^^20160222542236924^20160222154244|aaa";
		String[] successArr = success_details.split("\\|");
		for (String str : successArr) {
			String[] detailArr = str.split("\\^");
			System.out.println(detailArr[0]);
		}
	}

}
