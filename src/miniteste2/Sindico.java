package miniteste2;

import eduni.simjava.*;

public class Sindico extends Sim_entity{
	private Sim_port in;
	  private int index;
	  private int state;

	  public static final int SINK_BLOCKED = 0;
	  public static final int SINK_OK      = 1;

	  public Sindico(String name, int index, int state) {
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
	      sim_pause(1.234);
	      sim_schedule(in,0.0,1);
	    }
	    System.out.println("Exiting body S");
	  }
}
