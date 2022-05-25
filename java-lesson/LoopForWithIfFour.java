public class LoopForWithIfFour{
	public static void main(String[] args){
		int count = 10;

		for(int i=0; i<count; i++){
			for(int j=0; j<count; j++){
				if(i+j==count-1 || i==count-1 || j==count-1){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
