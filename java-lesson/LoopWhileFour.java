public class LoopWhileFour{
	public static void main(String[] args){
		int count = 10;

		int i=0;
		while(i<count){
			int j=0;
			while(j<i+1){
				System.out.print("*");
				j++;
			}
			System.out.println();
			i++;
		}
	}
}
