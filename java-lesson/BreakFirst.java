public class BreakFirst{
	public static void main(String[] args){
		int number = 1;		// nacalnoe cislo
		int sum = 0;		// peremennaya dlya hraneniya summy
		int count = 0;		// kolicestvo cisel - v nacale ni odnogo
		int max = 300;		// predel summy
		
		for(;;){
			sum += number;		// uvelicivaem summu
			if(sum > 300){		// proveryaem summu - esli bolse
				break;			// to eta tocka yavlyaetsa tockoy vyhoda
			}else{
				count++;		//a zdes prosto uvelicivaem kolicestvo
			}
			number += 10;		// uvelicivaem cislo na 10
		}
		System.out.println("count = " + count);
	}
}
