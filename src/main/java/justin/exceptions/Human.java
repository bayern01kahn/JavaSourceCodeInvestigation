package justin.exceptions;

class Annoyance extends Exception {
}

class Sneeze extends Annoyance {
}

class Human {

	public static void main(String[] args) {
		try {
			try {
				throw new Sneeze();
			} 
			catch ( Annoyance a ) {
				System.out.println("Caught Annoyance");
				throw a;
			}
		} 
		catch ( Sneeze s ) {
			System.out.println("Caught Sneeze");
			return ;
		}finally{
			System.out.println("Hello World");
		}
		
	}	
}