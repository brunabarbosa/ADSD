package miniteste2;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_negexp_obj;

class Source extends Sim_entity {
	private Sim_port enqueue;
	private Sim_negexp_obj src_hold;
	private Sim_stat stat;
	
	public Source(String name) {
		super(name);
		enqueue = new Sim_port("enqueue");
		add_port(enqueue);
		src_hold = new Sim_negexp_obj("iatime", 10.0);
		add_generator(src_hold);
		stat = new Sim_stat();
		stat.add_measure("Generation rate", Sim_stat.RATE_BASED);
		set_stat(stat);
	}

	public void body() {
		while (Sim_system.running()) {
			stat.update("Generation rate", Sim_system.sim_clock());
			sim_schedule(enqueue, 0.0, 0);
			sim_pause(src_hold.sample());
		}
	}
}