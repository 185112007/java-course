public class CheckFirst{
	public static void main(String[] args){
		int count = 10;
		
		int i = 0;
		while(i++ < count){
			System.out.print("*");
		}
		while(++i < count);
		System.out.println();
	}
}
