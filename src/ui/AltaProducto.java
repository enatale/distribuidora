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
 * Servlet implementation class AltaProducto
 */
@WebServlet("/AltaProducto")
public class AltaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaProducto() {
        super();
        // 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Empleado empleado = (Empleado) request.getSession().getAttribute("usuario");
		String descripcion = ((String)request.getParameter("txtDescripcion")).trim();
		
		String mensaje ="";
		try{
			if(empleado!=null && empleado.getLegajo()!=0){
				if(descripcion.equals("")){
					mensaje+="El campo descripcion del producto no puede estar en blanco\n";
				}
				if (request.getParameter("txtStock").equals("")) {
					mensaje+="El campo stock no puede estar en blanco";
				}
				if (request.getParameter("txtFecha").equals("")) { mensaje+="El campo fecha no puede estar en blanco";
				}
				if (request.getParameter("txtImporte").equals("")) {
					mensaje+="El campo importe no puede estar en blanco";
				}
				
				if (!mensaje.equals("")){
					request.setAttribute("mensaje", mensaje);
					request.getRequestDispatcher("altaProducto.jsp").forward(request, response);
				} else{
					int stock=Integer.parseInt(request.getParameter("txtStock"));
					float importe=Float.parseFloat(request.getParameter("txtImporte"));
					String fechaStr = ((String)request.getParameter("txtFecha")).trim();
					Date fecha_desde = null;
				    fecha_desde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
				   
					Producto pr= new Producto();
					pr.setDescripcion(descripcion);
					pr.setStock(stock);
					pr.setImporte(importe);
					pr.setFecha(fecha_desde);
					CtrlProducto producto;
					
						producto = new CtrlProducto();
						
							producto.agregarProducto(pr);
						    request.setAttribute("mensajeConfirmacion", "El producto fue agregado con exito");
						    request.getRequestDispatcher("altaProducto.jsp").forward(request, response);
						
						}
			}else{
			throw new ApplicationException("debe estar logueado como empleado para actualizar el stock", null);
			}
		}  catch (NumberFormatException e) {
			String msj="";
			if(!esEntero(request.getParameter("txtStock"))) msj+="El código de producto debe ser un número entero. \n";
			if(!esFloat(request.getParameter("txtImporte"))) msj+="El importe debe ser un numero\n";
			request.setAttribute("mensaje", msj);
			request.getRequestDispatcher("altaProducto.jsp").forward(request, response);
		}catch(ParseException e){

			request.setAttribute("mensaje", "formato de la fecha no es valido, intente con YYYY-mm-dd");
			request.getRequestDispatcher("altaProducto.jsp").forward(request, response);
		}catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("altaProducto.jsp").forward(request, response);
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
	

}
