/*
Logical Operations
*/
public class Fifth{
	public static void main(String[] args){
		boolean a = true;
		boolean b = false;
		boolean c = true;
		boolean result = a && (b || c);	// result = true && (false || true) = true && true = true
		System.out.println("result = " + result);
	}
}
