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
				importe=Float.parseFloat(request.getParameter("txtImporte"));	
				ctrl.actualizarPrecio(pr,importe, fecha_desde);

			    request.setAttribute("mensajeConfirmacion", "El precio fue modificado con exito");
			    request.getRequestDispatcher("actualizarStock.jsp").forward(request, response);
			}
		}else{
			throw new ApplicationException("debe estar logueado como empleado para actualizar el stock", null);
		}
		}catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("actualizarStock.jsp").forward(request, response);
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