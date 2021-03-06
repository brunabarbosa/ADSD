package miniteste2;

import eduni.simjava.Sim_system;

class Queue {
	public static void main(String args[]) {
		Sim_system.initialise();
		Morador customers = new Morador("Customers");
		Sindico service = new Sindico("Service");
		Sim_system.set_trace_detail(true, true, true);
		Sim_system.link_ports("Customers", "enqueue", "Service", "arrival");
		Sim_system.set_termination_condition(Sim_system.EVENTS_COMPLETED,
				"Service", 0, 10, false);
		Sim_system.run();
	} 
}
