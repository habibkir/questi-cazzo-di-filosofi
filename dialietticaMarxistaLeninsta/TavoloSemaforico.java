package dialietticaMarxistaLeninsta;

import java.util.concurrent.*;

public class TavoloSemaforico {
	/* facciamo che si generalizza un pochino, giusto per */
	Semaphore[] bacchette;

	public TavoloSemaforico(int dimensione) {
		bacchette = new Semaphore[dimensione];
		for(int i = 0; i<bacchette.length; ++i) {
			bacchette[i] = new Semaphore(1);
		}
	}

	void prendiBacchetta (int i) throws InterruptedException {
		bacchette[i].acquire();
	}

	void rilasciaBacchetta (int i) throws InterruptedException {
		bacchette[i].release();
	}
	
	public static void main(String[] possoAncheChiamarmiInUnAltroModo)
			throws InterruptedException {
		int dimensione = 5;
		TavoloSemaforico ts = new TavoloSemaforico (dimensione);
		Filosofi ff = new Filosofi(dimensione, ts);
		ff.startAll();
		/* ora diamogli un po' di tempo */
		Thread.sleep(10_000);
		ff.interruptAll();
		ff.joinAll();
		
		ff.printStatisticsAll();
	}
}