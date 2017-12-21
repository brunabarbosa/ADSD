package miniteste2;

import eduni.simjava.Sim_system;

public class Main {

	public static void main(String[] args) {
		Sim_system.initialise();
	    Sim_system.add(new Morador("Sender", 1, Morador.SRC_OK));
	    Sim_system.add(new Sindico("Receiver", 2, Sindico.SINK_OK));
	    Sim_system.link_ports("Sender", "out", "Receiver", "in");
	    Sim_system.run();
	}
}
