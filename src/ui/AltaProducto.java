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
import entidades.Cliente;
import entidades.Empleado;
import entidades.Persona;
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
        // TODO Auto-generated constructor stub
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
		
		String descripcion = ((String)request.getParameter("txtDescripcion")).trim();
		int stock = Integer.parseInt(request.getParameter("txtStock"));
		String fechaStr = ((String)request.getParameter("txtFecha"));
		SimpleDateFormat sfd= null;
		Date fecha_desde = null;
		try {
			fecha_desde = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Float importe = Float.parseFloat(request.getParameter("txtImporte"));
		String mensaje ="";
		if(descripcion.equals("")){
			mensaje+="El campo descripcion del producto no puede estar en blanco\n";
		}
		if (stock==0) {
			mensaje+="El campo stock no puede estar en blanco";
		}
		if (fecha_desde.equals(null)) { mensaje+="El campo fecha no puede estar en blanco";
		}
		if (importe==0) {
			mensaje+="El campo importe no puede estar en blanco";
		}
		if (!mensaje.equals("")){
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("altaProducto.jsp").forward(request, response);
		} else{
			Producto pr= new Producto();
			pr.setDescripcion(descripcion);
			pr.setStock(stock);
			pr.setImporte(importe);
			pr.setFecha(fecha_desde);
			CtrlProducto producto;
			
				producto = new CtrlProducto();
				try {
					producto.agregarProducto(pr);
				    request.setAttribute("mensajeConfirmacion", "El producto fue agregado con exito");
				    request.getRequestDispatcher("altaProducto.jsp").forward(request, response);
				} catch (ApplicationException e) {
					request.setAttribute("mensaje", e.getMessage());
					request.getRequestDispatcher("altaProducto.jsp").forward(request, response);
				}
				
			
			
		}
	}

}
