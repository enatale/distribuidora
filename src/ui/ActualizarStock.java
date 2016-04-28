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
@WebServlet("/ActualizarStock")
public class ActualizarStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mensaje="";
		int cantidad=0;
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
				if(pr.equals(null)){

					request.setAttribute("mensaje", "No se encontro producto con el código ingresado");
					request.getRequestDispatcher("actualizarStock.jsp").forward(request, response);
				}else{
				cantidad=Integer.parseInt(request.getParameter("txtCantidad"));		
				ctrl.actualizarStock(pr,cantidad);

			    request.setAttribute("mensajeConfirmacion", "El stock del producto fue modificado con exito");
			    request.getRequestDispatcher("actualizarStock.jsp").forward(request, response);
				}
			}
		}else{
			throw new ApplicationException("debe estar logueado como empleado para actualizar el stock", null);
		}
		}catch (NumberFormatException e) {
			String msj="";
			if(!esEntero(request.getParameter("txtCod"))) msj+="El código de producto debe ser un número entero. \n";
			if(!esEntero(request.getParameter("txtCantidad"))) msj+="La cantidad debe ser un número entero. \n";
			request.setAttribute("mensaje", msj);
			request.getRequestDispatcher("actualizarStock.jsp").forward(request, response);
		}catch (NullPointerException e) {
			request.setAttribute("mensaje", "No se encontro producto con el código ingresado");
			request.getRequestDispatcher("actualizarStock.jsp").forward(request, response);
		}catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("actualizarStock.jsp").forward(request, response);
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
