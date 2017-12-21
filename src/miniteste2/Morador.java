package miniteste2;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_normal_obj;

public class Morador extends Sim_entity{

	private Sim_port out;
	private int index;
	private int state;
	
	public static final int SRC_OK = 0;
	public static final int SRC_BLOCKED = 1;
	
	public Morador(String name, int index, int state){
		super(name);
		this.index = index;
		this.state = state;
		out = new Sim_port("out");
		add_port(out);
	}
	
	public void body() {
	    Sim_event ev = null;
	    Sim_normal_obj delay;
	    Sim_accum results;
	    double last_t;
	    int i;

	    System.out.println("About to do body S");
	    // Make a normally distributed rnd number generator
	    delay = new Sim_normal_obj("Delay Object", 10.0, 5.0, 123);
	    // Accum object to record stats
	    results = new Sim_accum();

	    Sim_accum accum_test = new Sim_accum();
	    accum_test.update(1,10);
	    accum_test.update(1,15);
	    accum_test.update(1,20);
	    
	    sim_trace(1, "C accum_test min "+accum_test.min() + 
		      " max " + accum_test.max() + 
		      " interval sum " + accum_test.interval_sum() +
		      " avg " + accum_test.avg());


	    last_t = 0.0;
	    for (i=0; i<30; i++) {
	      sim_schedule(out,0.0,0);
	      state = SRC_BLOCKED;
	      sim_wait(ev);
	      results.update(Sim_system.sim_clock()-last_t, 0.0);
	      last_t = Sim_system.sim_clock();
	      state = SRC_OK;
	      //sim_hold() is deprecated
	      sim_pause(delay.sample());
	      results.update(Sim_system.sim_clock()-last_t, 100.0);
	      last_t = Sim_system.sim_clock();
	      sim_trace(1,"C Src loop index is "+i);
	    }
	    sim_trace(1, "C Interval total "+results.interval_sum()+
	                         " Average "+results.avg());
	  
	}
	
	
}
