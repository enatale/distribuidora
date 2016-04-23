package ui.pedido;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appExceptions.ApplicationException;
import entidades.Cliente;
import entidades.Estado_pedido;
import entidades.Linea_pedido;
import entidades.Pedidos;
import entidades.Producto;
import negocio.CtrlPedidos;

/**
 * Servlet implementation class Pedido
 */
@WebServlet("/pedido")
public class Pedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pedidos pedido = (Pedidos) request.getSession().getAttribute("pedido");
		Cliente cliente = (Cliente) request.getSession().getAttribute("usuario");
		ArrayList<Linea_pedido> items;
		Producto producto;
		String mensaje="";
		CtrlPedidos ctrl= new CtrlPedidos();
		try{
			if(cliente!=null&&cliente.getCodCliente()!=0){
				if(pedido==null){
					pedido= new Pedidos();
					pedido.setCliente(cliente);
					pedido.setFecha_pedido(new Date());
					pedido.setEstado(new Estado_pedido(1,"Pendiente"));
					items= new ArrayList<Linea_pedido>();
				} else {
					items = pedido.getLineas();
				}
				if(request.getParameter("txtCod").equals("")){
					mensaje+= "El codigo de producto no puede estar vacio\n";
				}
				if(request.getParameter("txtCantidad").equals("")){
					mensaje+= "La cantidad a pedir no puede estar vacia\n";
				}
				if(!mensaje.equals("")){
					throw new ApplicationException(mensaje, null);
				}else{
					int codigo=Integer.valueOf(request.getParameter("txtCod"));
					producto=ctrl.getByCodigo(codigo);
					for (Linea_pedido linea_pedido : items) {
						if(linea_pedido.getProducto().getCodProducto()==producto.getCodProducto()){
							throw new ApplicationException("El producto ingresado ya existe en su pedido. Eliminelo y vuelva a intentarlo", null);
						}
					}
					int cantidad=Integer.valueOf(request.getParameter("txtCantidad"));
					int stock = ctrl.obtenerStock(producto);
					if(stock>cantidad){
						items.add(new Linea_pedido(producto,cantidad));
						pedido.setLineas(items);
						request.getSession().setAttribute("pedido", pedido);
						response.sendRedirect("pedido.jsp");
					} else{
						throw new ApplicationException("No hay stock suficiente para esa cantidad", null);
					}
				}
			}
			else {
				throw new ApplicationException("Para hacer un pedido debe iniciar sesión como cliente", null);
			}
		}  catch (NumberFormatException e) {
			String msj="";
			if(!esEntero(request.getParameter("txtCod"))) msj+="El código de producto debe ser un número entero. \n";
			if(!esEntero(request.getParameter("txtCantidad"))) msj+="La cantidad debe ser un número entero. \n";
			request.setAttribute("mensaje", msj);
			request.getRequestDispatcher("pedido.jsp").forward(request, response);
		} catch (NullPointerException e) {
			request.setAttribute("mensaje", "No se encontro producto con el código ingresado");
			request.getRequestDispatcher("pedido.jsp").forward(request, response);
		} catch (ApplicationException e){
			request.setAttribute("mensaje", e.getMessage());
			request.getRequestDispatcher("pedido.jsp").forward(request, response);
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
		doGet(request, response);
	}

}
