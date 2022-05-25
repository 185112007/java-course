public class ShortBool{
	public static void main(String[] args){
		boolean a = true;
		int b = 0;

		boolean result = a || (++b > 0);	// result = true
		System.out.println("result = " + result);
		System.out.println("b = " + b);		// b = 0
	}
}
