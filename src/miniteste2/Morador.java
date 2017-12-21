package miniteste2;

import eduni.simjava.*;

public class Morador extends Sim_entity{

	private Sim_port out;
	private int index;
	private int state;
	
	public Morador(String name, int index, int state){
		super(name);
		this.index = index;
		this.state = state;
		out = new Sim_port("out");
		add_port(out);
	}
}
