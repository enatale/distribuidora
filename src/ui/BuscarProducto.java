package ui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CtrlPedidos;
import negocio.CtrlProducto;
import appExceptions.ApplicationException;
import entidades.Empleado;
import entidades.Pedidos;
import entidades.Producto;

/**
 * Servlet implementation class buscarProducto
 */
@WebServlet("/BuscarProducto")
public class BuscarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarProducto() {
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
		float importe=0;
		CtrlProducto ctrl= new CtrlProducto();
		Producto pr;
		Empleado empleado = (Empleado) request.getSession().getAttribute("usuario");
		
		try{
		if(empleado!=null && empleado.getLegajo()!=0){
			if(request.getParameter("txtCod").equals("")){
				mensaje+= "El codigo de producto no puede estar vacio\n";
			}
			
			
			if(!mensaje.equals("")){
				throw new ApplicationException(mensaje, null);
			}else{
				pr=new Producto();
				int codigo=Integer.valueOf(request.getParameter("txtCod"));
				pr=ctrl.getByCodigo(codigo);	
				if(pr.equals(null)){

					request.setAttribute("mensaje", "No se encontro producto con el código ingresado");
					request.getRequestDispatcher("buscarProducto.jsp").forward(request, response);
				}else{
				
			    request.setAttribute("producto", pr);
			    request.getRequestDispatcher("modificarProducto.jsp").forward(request, response);
				}
			}
		}else{
			throw new ApplicationException("debe estar logueado como empleado para modificar el producto", null);
		}
		}catch (NumberFormatException e) {
			String msj="";
			if(!esEntero(request.getParameter("txtCod"))) msj+="El código de producto debe ser un número entero. \n";
			
			request.setAttribute("mensaje", msj);
			request.getRequestDispatcher("buscarProducto.jsp").forward(request, response);
		}catch (NullPointerException e) {
			request.setAttribute("mensaje", "No se encontro producto con el código ingresado");
			request.getRequestDispatcher("buscarProducto.jsp").forward(request, response);
		} catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("buscarProducto.jsp").forward(request, response);
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