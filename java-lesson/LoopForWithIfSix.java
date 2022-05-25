public class LoopForWithIfSix{
	public static void main(String[] args){
		int count = 10;

		/*
		        *     
			   * *
			  *   *
			 *     *
			*    	*
		   *		 *
			*		*
			 *	   *
			  *	  *
			   * *
				*

					       j
				-------------------
			i=0|           5    
			i=1|         4   6  
			i=2|       3       7
			i=3|	 2		     8
			i=4|   1			   9
			i=5| 0					 10
			i=6|   1			   9
			i=7| 	 2			 8
			i=8|	   3	   7
			i=9|		 2   6
		   i=10|		   5 
		*/

		for(int i=0; i<=count; i++){
			for(int j=0; j<=count; j++){
				if(i+j==count/2|| i-j==count/2 || j-i==count/2 || i+j==(count+count/2)){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
