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

import negocio.CtrlPedidos;
import appExceptions.ApplicationException;
import entidades.Empleado;
import entidades.Pedidos;

/**
 * Servlet implementation class BuscarPedidos
 */
@WebServlet("/ListadoPedidos")
public class ListadoPedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoPedidos() {
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
		String fechaStrIni = ((String)request.getParameter("txtFechaIni"));
		String fechaStrFin = ((String)request.getParameter("txtFechaFin"));
		String estado = ((String)request.getParameter("Estado"));
		SimpleDateFormat sfd= null;
		ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();
		try{
		if(empleado!=null && empleado.getLegajo()!=0){
			if(estado.equals("")){
				mensaje+= "El estado del pedido no puede estar vacio\n";
			}
			if(fechaStrIni.equals("")){
				mensaje+= "La fecha de inicio de busqueda no puede estar vacio\n";
			}
			if(fechaStrFin.equals("")){
				mensaje+= "La fecha de fin de busqueda no puede estar vacio\n";
			}
			if(!mensaje.equals("")){
				throw new ApplicationException(mensaje, null);
			}else{
				Date fInicio=new SimpleDateFormat("yyyy-MM-dd").parse(fechaStrIni);
				Date fFin=new SimpleDateFormat("yyyy-MM-dd").parse(fechaStrFin);
				pedidos=ctrl.buscarPedidosFecha(fInicio,fFin,estado);
				request.setAttribute("pedidos", pedidos);
				request.getRequestDispatcher("listadoPedidos.jsp").forward(request, response);
				}
		}
		}catch(ParseException e){

			request.setAttribute("mensaje", "formato de la fecha no es valido, intente con YYYY-mm-dd");
			request.getRequestDispatcher("buscarPedidos.jsp").forward(request, response);
		
		}catch (NullPointerException e) {
			request.setAttribute("mensaje", "No se encontro pedidos");
			request.getRequestDispatcher("buscarPedidos.jsp").forward(request, response);
		} catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("buscarPedidos.jsp").forward(request, response);
		}

}
}
