public class LoopWhileTwo{
	public static void main(String[] args){
		int count = 10;

		int i = 0;
		// imenno tak - i++. Esli tak: ++i, to simvolov budet menshe
		while(i++ < count){
			System.out.print(i);
		}
		System.out.println();
	}
}
