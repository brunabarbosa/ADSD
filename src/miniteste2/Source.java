package miniteste2;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_normal_obj;

class Source extends Sim_entity {
	  private Sim_port out;
	  private int index;
	  private int state;

	  public static final int SRC_OK      = 0;
	  public static final int SRC_BLOCKED = 1;

	  public Source(String name, int index, int state) {
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
	      sim_hold(delay.sample());
	      results.update(Sim_system.sim_clock()-last_t, 100.0);
	      last_t = Sim_system.sim_clock();
	      sim_trace(1,"C Src loop index is "+i);
	    }
	    sim_trace(1, "C Interval total "+results.interval_sum()+
	                         " Average "+results.avg());
	  }
	  
	  public static void main(String[] args) {
		  Sim_system.initialise();
		    Sim_system.add(new Source("Sender", 1, Source.SRC_OK));
		    Sim_system.add(new Sink("Receiver", 2, Sink.SINK_OK));
		    Sim_system.link_ports("Sender", "out", "Receiver", "in");
		    Sim_system.run();
	}
	}

	class Sink extends Sim_entity {
	  private Sim_port in;
	  private int index;
	  private int state;

	  public static final int SINK_BLOCKED = 0;
	  public static final int SINK_OK      = 1;

	  public Sink(String name, int index, int state) {
	    super(name);
	    this.index = index;
	    this.state = state;
	    in = new Sim_port("in");
	    add_port(in);
	  }

	  public void body() {
	    Sim_event ev = null;
	    int i = 0;

	    System.out.println("About to do body R");
	    while(true) {
	      i++; if(i>50) break;
	      sim_wait(ev);
	      sim_hold(1.234);
	      sim_schedule(in,0.0,1);
	    }
	    System.out.println("Exiting body S");
	  }
	}


