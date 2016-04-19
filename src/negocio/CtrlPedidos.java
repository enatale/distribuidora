package negocio;

import java.util.ArrayList;

import datos.*;
import entidades.Producto;

public class CtrlPedidos {
	
	dataPedidos dp;

	public ArrayList<Producto> getAll(int desde, int hasta) throws ClassNotFoundException{
		return new dataPedidos().getAll(desde, hasta);
	}
}
