public class LoopForWithIfFive{
	public static void main(String[] args){
		int count=11;
		
		for(int i=count/2; i<count; i++){
			for(int j=0; j<count; j++){
				if(i==count-1 || i==j || i+j==count-1){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
