package miniteste2;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;

public class Sindico extends Sim_entity{
	private Sim_port in;
	  private int index;
	  private int state;
	  
	  Sim_stat stat;


	  public static final int SINK_BLOCKED = 0;
	  public static final int SINK_OK      = 1;

	  public Sindico(String name, int index, int state) {
	    super(name);
	    this.index = index;
	    this.state = state;
	    in = new Sim_port("in");
	    add_port(in);
	    stat = new Sim_stat();
	    stat.add_measure(Sim_stat.ARRIVAL_RATE);
        stat.add_measure(Sim_stat.QUEUE_LENGTH); 
        stat.add_measure(Sim_stat.WAITING_TIME); 
        stat.add_measure(Sim_stat.THROUGHPUT);
        set_stat(stat);

	  }

	  public void body() {
	    Sim_event ev = null;
	    int i = 0;

	    System.out.println("About to do body R");
	    while(true) {
	      i++; if(i>50) break;
	      sim_wait(ev);
	      sim_pause(100);
	      sim_schedule(in,0.0,1);
	    }
	    System.out.println("Exiting body S");
	  }
}
