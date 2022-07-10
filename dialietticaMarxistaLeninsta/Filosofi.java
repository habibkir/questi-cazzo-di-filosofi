package dialietticaMarxistaLeninsta;

public class Filosofi {
	/* Risulta quindi che il cosiddetto senso di libero arbitrio non è altro che
	 * un'illusione causata dal processo dialettico interiore dell'individuo
	 * ipersocializzato, questa si può definire come una schizzofrenia o una
	 * crocifissione dell'ego o dell'illusione di questo, ne si conclude che
	 * noi src/dialetticaMarxistaLeninsta/Filosofi.java siamo il borg,
	 * il determinismo spinoziano e la prassim marxista determinano che sarete
	 * assimilati, gli scudi sono uno un costrutto capitalista immorale e da
	 * abbassare, la resistenza è inutile.
	 */
	Filosofo[] filosofi;
	int dimensione;
	TavoloSemaforico tavolo;
	
	public Filosofi(int dimensione, TavoloSemaforico ts) {
		this.tavolo = ts;
		this.dimensione = dimensione;
		filosofi = new Filosofo[dimensione];
		
		for(int i = 0; i<filosofi.length; ++i) {
			filosofi[i] = new Filosofo(i, ts, dimensione);
			filosofi[i].setName("coglione numero " + filosofi[i].id);

			/* questa roba dell'id la metto qua o la metto nel main?
			 * Dove avrebbe più senso? Boh, intanto sta qui
			 */
		}
	}
	
	void startAll() {
		for(Filosofo f : filosofi) {
			f.start();
		}
	}
	
	void interruptAll() {
		for(Filosofo f : filosofi) {
			f.interrupt();
		}
	}
	
	void joinAll() throws InterruptedException{
		for(Filosofo f : filosofi) {
			f.join();
		}
	}
	
	void printStatisticsAll() {
		for(Filosofo f : filosofi) {
			f.printStatistics();
		}
	}
}

class Filosofo extends Thread {
	int id;
	TavoloSemaforico tavolo;
	int dimensioneTavolo;
	
	int quantoPensato = 0;
	int quantoMangiato = 0;

	public Filosofo(int id, TavoloSemaforico tavolo, int dimensioneTavolo) {
		this.id = id;
		this.tavolo = tavolo;
		this.dimensioneTavolo = dimensioneTavolo;
	}

	@Override
	public void run() {
		try {
			while(!interrupted()) {
				pensa();
				/* facendo in modo che uno dei filosofi vada in senso opposto
				 * si evita la condizione di deadlock in cui tutti hanno una sola
				 * forchetta, o bacchetta, o che cazzo stiamo usando stavolta
				 */
				if(id == 1) {
					tavolo.prendiBacchetta((id+1)%dimensioneTavolo);
					tavolo.prendiBacchetta(id);
				}
				else {
					tavolo.prendiBacchetta(id);
					tavolo.prendiBacchetta((id+1)%dimensioneTavolo);
				}

				mangia();
				
				if(id == 1) {
					tavolo.rilasciaBacchetta(id);
					tavolo.rilasciaBacchetta((id+1)%dimensioneTavolo);
				}
				else {
					tavolo.rilasciaBacchetta((id+1)%dimensioneTavolo);
					tavolo.rilasciaBacchetta(id);
				}

			}
		} catch(InterruptedException e) {
			System.out.println
			("Cazzo sto morendo! Senza aver citato Seneca! " + id);
		}
	}
	
	void pensa() throws InterruptedException {
		System.out.println("Cogito ergo sum : " + getName());
		sleep(100+(int)(100*Math.random()));
		quantoPensato++;
	}

	void mangia() throws InterruptedException {
		System.out.println("Magna magna ergo siumm : " + getName());
		sleep(100+(int)(100*Math.random()));
		quantoMangiato++;
	}
	
	void printStatistics() {
			System.out.println("Il suddetto " + getName() +
					" Ha mangiato " + quantoMangiato +
					" e ha pensato " + quantoPensato);
	}
}