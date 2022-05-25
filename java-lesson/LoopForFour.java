public class LoopForFour{
	public static void main(String[] args){
		int count = 10;

		//vneshniy cikl
		for(int i=0; i<count; i++){
			for(int j=0; j<count; j++){
				System.out.print("*");
			}
			//perevod na sleduyushuyu stroku
			System.out.println();
		}
	}
}
