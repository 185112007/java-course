public class LoopForWithIfTwo{
	public static void main(String[] args){
		int count = 10;

		for(int i=0; i<count; i++){
			for(int j=0; j<i+1; j++){
				// zdes uslovie daje nemnogo proshe
				if(j == 0 || j == i || i == count-1){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
