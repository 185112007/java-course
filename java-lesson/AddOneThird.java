public class AddOneThird{
	public static void main(String[] args){
		int b = 5;
		int a = 12;
		int c = ++a - b++;	//c = 13 - 5 = 8
		System.out.println("c = " + c);
		int d = b++ * 2;	//d = 6 * 2 = 12
		System.out.println("d = " + d);
	}
}
