import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Principal2 implements Runnable{

	private int id;	
	private static buffer buf=new buffer();

	private static monitor moni=new monitor();//crear en cada uno de los hilos (monitor)
	
	
	public Principal2(int id){
		this.id=id;
	}
	
	public void run(){

	if(id==0){
		int elemento;
		while(true){
		elemento=buf.leer();
			if(elemento!=-1){
			System.out.println("el elemnto de buffer es :"+elemento);
		
			}
			
			try{
			
			Thread.sleep(1000);//PARA QUE NO SE E EXCESIVAMETE RAPIDO
			}catch(InterruptedException e){
			
			e.printStackTrace();
			
			}
		
		
		}
	}
	else{

		while(true){
		buf.escribir();
		try{
		Thread.sleep(200);//PARA EL PRODUCTOR
			
			}catch (InterruptedException e){
				e.printStackTrace();
			}
	
		}
	}
}

	
	
	
	
	public static void main(String[] args){
	
	Runtime runtime=Runtime.getRuntime();
	int nNucleos=runtime.availableProcessors();//cuantos hilos logicos tiene mi cpu
	
	ExecutorService poolLector=Executors.newFixedThreadPool(nNucleos);
	for(int i=0;i<nNucleos;i++){
		Runnable runnable=new Principal2(0);
		poolLector.execute(runnable);
		
	}
	
	
	poolLector.shutdown();
	
	ExecutorService poolEscritor=Executors.newFixedThreadPool(2);
	for(int i=0;i<2;i++){
		Runnable runnable=new Principal2(1);//productor le do a un id de 1
		poolEscritor.execute(runnable);
		
	}
	poolEscritor.shutdown();
	
	while(!poolLector.isTerminated());

	
		}
	}
