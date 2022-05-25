public class LoopForFive{
	public static void main(String[] args){
		int count = 10;

		for(int i=0; i<count; i++){
			for(int j=0; j<i+1; j++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
