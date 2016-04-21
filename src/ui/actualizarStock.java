package ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlPedidos;
import negocio.CtrlProducto;
import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Empleado;
import entidades.Producto;

/**
 * Servlet implementation class actualizarStock
 */
@WebServlet("/actualizarStock")
public class actualizarStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actualizarStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mensaje="";
		int total=0;
		CtrlProducto ctrl= new CtrlProducto();
		Producto pr;
		Empleado empleado = (Empleado) request.getSession().getAttribute("usuario");
		try{
		if(empleado!=null && empleado.getLegajo()!=0){
			if(request.getParameter("txtCod").equals("")){
				mensaje+= "El codigo de producto no puede estar vacio\n";
			}
			if(request.getParameter("txtCantidad").equals("")){
				mensaje+= "La cantidad a agregar a pedir no puede estar vacia\n";
			}
			if(!mensaje.equals("")){
				throw new ApplicationException(mensaje, null);
			}else{
				int codigo=Integer.valueOf(request.getParameter("txtCod"));
				pr=ctrl.getByCodigo(codigo);	
				total=pr.getStock()+Integer.parseInt(request.getParameter("txtCantidad"));
				pr.setStock(total);
				ctrl.actualizarStock(pr);
			}
		}else{
			throw new ApplicationException("debe estar logueado como empleado para actualizar el stock", null);
		}
		}catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("actualizarStock.jsp").forward(request, response);
		}
	}

}
