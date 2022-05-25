public class LoopForWithIfOne{
	public static void main(String[] args){
		int count = 10;

		for(int i=0; i<count; i++){
			for(int j=0; j<count; j++){
				if(j==0 || j==count-1 || i==0 || i==count-1){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
