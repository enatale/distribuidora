package ui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import entidades.Producto;
import negocio.CtrlPedidos;

/**
 * Servlet implementation class ReportePrecios
 */
@WebServlet("/reportePrecios")
public class ReportePrecios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportePrecios() {
        super();
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inicio,pagina,totalProductos,totalPaginas;
		Date fecha;
		CtrlPedidos ctrl = new CtrlPedidos();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		int cant_por_pagina=10;
		if(request.getParameter("pagina")!=null){
			pagina = Integer.parseInt(request.getParameter("pagina"));
		} else pagina=0;
		
		if(pagina==0){
			inicio=0;
			pagina=1;
		} else{
			inicio = (pagina-1)*cant_por_pagina;
		}
		try {
			String fechastring = request.getParameter("fecha");
			fecha=new SimpleDateFormat("yyyy-MM-dd").parse(fechastring);
			totalProductos= ctrl.getCantProductos();
			totalPaginas=  (int) Math.ceil((float)totalProductos/(float)cant_por_pagina);
			productos=ctrl.getAll(inicio, cant_por_pagina, fecha);
			request.setAttribute("totalProductos", totalProductos);
			request.setAttribute("totalPaginas", totalPaginas);
			request.setAttribute("productos", productos);
			request.setAttribute("pagina", pagina);
			request.setAttribute("fecha", fechastring);
			request.getRequestDispatcher("reportePrecios.jsp").forward(request, response);
			
		} catch (ApplicationException e) {
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("reportePrecios.jsp").forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("mensaje", "La fecha debe ser una fecha válida en el formato: AAAA-MM-DD" );
			request.getRequestDispatcher("reportePrecios.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("reportePrecios.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		doGet(request, response);
	}

}
