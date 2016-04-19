package ui;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Producto;
import negocio.CtrlPedidos;

import appExceptions.ApplicationException;

/**
 * Servlet implementation class Productos
 */
@WebServlet("/Productos")
public class Productos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Productos() {
        // TODO Auto-generated constructor stub
    	//super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			int cant_por_pagina = 1;
			int limitDesde=0,limitHasta=0;
			CtrlPedidos db = new CtrlPedidos();
			ArrayList<Producto> prod = new ArrayList<Producto>();
			
			try {
				
			    String limit =(String)request.getAttribute(("limit"));
			    // como recuperar el valor de la request 
				if (limit==null||limit=="") limit="0";
				else {limit+=cant_por_pagina;} 
			    limitDesde=Integer.parseInt(limit);
				
			    limitHasta=limitDesde+cant_por_pagina;
				prod=db.getAll(limitDesde,limitHasta);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("limitHasta", limitHasta);
			request.setAttribute("productos", prod);
		request.getRequestDispatcher("productos.jsp").forward(request, response);
	}

}


