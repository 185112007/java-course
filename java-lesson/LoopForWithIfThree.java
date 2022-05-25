public class LoopForWithIfThree{
	public static void main(String[] args){
		int count = 10;

		for(int i=0; i<count; i++){
			
			for(int j=0; j<count; j++){
				/*
					i	j	count
					1	8	
					2	7
					3	6
					.	.
					8	1
				*/
				if(i== count-1 || i*j==0 || j== count-1 || i==j || i+j==count-1){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
