import java.util.Random;
import java.util.Vector;
public class buffer{


	private Random random=new Random(System.nanoTime());


	private int tam=10;
	private int pos=-1;
	private Vector<Integer> cola=new Vector();



	public synchronized int leer(){


		int elemento=-1;

		if ( pos <0){
			try{	
			System.out.println(" el vectro esta vacio y el consumidor se va  a dormir ");
			wait();
			}
			
			catch(InterruptedException e){
				e.printStackTrace();
				
				}
					
		
		}
		
		else{
		elemento=cola.get(pos);
		cola.remove(pos);
		pos--;
		
		}
		return elemento;

	}
	
	
	
	public synchronized void escribir(){
	
	
		pos++;
	
		if(pos>=tam){
			System.out.println("estas, completo...!");
		pos--;
		}
		
		else{
		cola.add(generar());
		
		notifyAll();
		
		
		}
	
	}
	
	public synchronized int generar(){
	return random.nextInt(10);
	}

}
