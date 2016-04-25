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

import negocio.CtrlProducto;
import appExceptions.ApplicationException;
import entidades.Empleado;
import entidades.Producto;

/**
 * Servlet implementation class ActualizarPrecio
 */
@WebServlet("/ActualizarPrecio")
public class ActualizarPrecio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarPrecio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mensaje="";
		float importe=0;
		CtrlProducto ctrl= new CtrlProducto();
		Producto pr;
		Empleado empleado = (Empleado) request.getSession().getAttribute("usuario");
		String fechaStr = ((String)request.getParameter("txtFecha"));
		SimpleDateFormat sfd= null;
		Date fecha_desde = null;
		try {
			fecha_desde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
		if(empleado!=null && empleado.getLegajo()!=0){
			if(request.getParameter("txtCod").equals("")){
				mensaje+= "El codigo de producto no puede estar vacio\n";
			}
			if(request.getParameter("txtImporte").equals("")){
				mensaje+= "El importe  no puede estar vacia\n";
			}
			if(request.getParameter("txtFecha").equals("")){
				mensaje+= "La fecha no puede estar vacia\n";
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
				importe=Float.parseFloat(request.getParameter("txtImporte"));	
				ctrl.actualizarPrecio(pr,importe, fecha_desde);

			    request.setAttribute("mensajeConfirmacion", "El precio fue modificado con exito");
			    request.getRequestDispatcher("actualizarStock.jsp").forward(request, response);
				}
			}
		}else{
			throw new ApplicationException("debe estar logueado como empleado para actualizar el stock", null);
		}
		}catch (NumberFormatException e) {
			String msj="";
			if(!esEntero(request.getParameter("txtCod"))) msj+="El código de producto debe ser un número entero. \n";
			if(!esFloat(request.getParameter("txtImporte"))) msj+="El importe debe ser un numero\n";
			request.setAttribute("mensaje", msj);
			request.getRequestDispatcher("actualizarPrecio.jsp").forward(request, response);
		}catch (NullPointerException e) {
			request.setAttribute("mensaje", "No se encontro producto con el código ingresado");
			request.getRequestDispatcher("actualizarPrecio.jsp").forward(request, response);
		} catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("actualizarPrecio.jsp").forward(request, response);
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
	private boolean esFloat(String cadena){
		try {
			Float.parseFloat(cadena);
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