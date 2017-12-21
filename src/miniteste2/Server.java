package miniteste2;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_negexp_obj;

class Server extends Sim_entity {
	private Sim_port arrival;
	private Sim_negexp_obj svr_hold;
	private Sim_stat stat;
	
	public Server(String name) {
		super(name);
		arrival = new Sim_port("arrival");
		add_port(arrival);
		svr_hold = new Sim_negexp_obj("stime", 8.5);
		add_generator(svr_hold);
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.UTILISATION);
		stat.add_measure(Sim_stat.SERVICE_TIME);
		stat.add_measure(Sim_stat.ARRIVAL_RATE);
		stat.add_measure(Sim_stat.WAITING_TIME);
		stat.add_measure(Sim_stat.THROUGHPUT);
		stat.add_measure(Sim_stat.QUEUE_LENGTH);
		stat.calc_proportions(Sim_stat.QUEUE_LENGTH, new double[] {0, 1, 2});
		stat.set_efficient(Sim_stat.SERVICE_TIME);
		stat.set_efficient(Sim_stat.QUEUE_LENGTH);
		set_stat(stat);
	}

	public void body() {
		Sim_event next = new Sim_event();
		while (Sim_system.running()) {
			sim_get_next(next);
			sim_process(svr_hold.sample());
			sim_completed(next);
		}
	}
}