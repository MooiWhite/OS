import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.PriorityQueue;

class proceso{
	String nombre;
	int id;
	int instr;
	int espacio_mem;
	int localidad_inicio;
	int localidad_fin;
	int instr_ejecutadas;

	public proceso(String nombre, int id, int instr, int espacio_mem, int localidad_inicio, int localidad_fin){
		this.nombre=nombre;
		this.id=id;
		this.instr=instr;
		this.espacio_mem=espacio_mem;
		this.localidad_inicio= localidad_inicio;
		this.localidad_fin=localidad_fin;
	}

	public void setName(String nombre){
		this.nombre=nombre;
	}

	public String getName(){
		return nombre;
	}

	public int get_id(){
		return id;
	}

	public int instr(){
		return instr;
	}
	public int espacio_mem(){
		return espacio_mem;
	}

	public void excute_proc(){
		this.instr=this.instr-5;
	}


}