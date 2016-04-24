package ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ui.pedido.Pedido;
import negocio.CtrlPedidos;
import negocio.CtrlProducto;
import appExceptions.ApplicationException;
import entidades.Empleado;
import entidades.Pedidos;
import entidades.Producto;

/**
 * Servlet implementation class BuscarPedido
 */
@WebServlet("/BuscarPedido")
public class BuscarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mensaje="";
		CtrlPedidos ctrl= new CtrlPedidos();
		Pedidos ped = new Pedidos();
		Empleado empleado = (Empleado) request.getSession().getAttribute("usuario");
		try{
		if(empleado!=null && empleado.getLegajo()!=0){
			if(request.getParameter("txtNro").equals("")){
				mensaje+= "El numero del pedido no puede estar vacio\n";
			}
			if(!mensaje.equals("")){
				throw new ApplicationException(mensaje, null);
			}else{
				int nroPedido=Integer.valueOf(request.getParameter("txtNro"));
				ped=ctrl.getByNroPedido(nroPedido);
				if(ped.equals(null)){

					request.setAttribute("mensaje", "No se encontro pedido con el numero ingresado");
					request.getRequestDispatcher("buscarPedido.jsp").forward(request, response);
				}else{
			    request.setAttribute("pedido", ped);
			    request.getRequestDispatcher("actualizarEstadoPedido.jsp").forward(request, response);
				}
			}
		}else{
			throw new ApplicationException("debe estar logueado como empleado para actualizar el stock", null);
		}
		} catch (NumberFormatException e) {
			String msj="";
			if(!esEntero(request.getParameter("txtNro"))) msj+="El código de producto debe ser un número entero. \n";
			request.setAttribute("mensaje", msj);
			request.getRequestDispatcher("buscarPedido.jsp").forward(request, response);
		} catch (NullPointerException e) {
			request.setAttribute("mensaje", "No se encontro pedido con el numero ingresado");
			request.getRequestDispatcher("buscarPedido.jsp").forward(request, response);
		} catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("buscarPedido.jsp").forward(request, response);
		}
	}
	
	private boolean esEntero(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException e2) {
			return false;
		}
	}
	}
	


