public class LongBool{
	public static void main(String[] args){
		boolean a = true;
		int b = 0;
		
		boolean result = a | (++b > 0);
		System.out.println("b = " + b);
	}
}
