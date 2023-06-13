package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans produto = new JavaBeans();

	public Controller() {
		super();
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			produtos(request, response);
		} else if (action.equals("/insert")) {
			novoProduto(request, response);
		} else if (action.equals("/select")) {
			listarProduto(request, response); 
		} else if (action.equals("/delete")) {
			removerProduto(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}


	protected void produtos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<JavaBeans> lista = dao.listarProdutos();
	
		request.setAttribute("produtos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("stockflow.jsp");
		rd.forward(request, response);

	}


	protected void novoProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		produto.setNome(request.getParameter("nome"));
		produto.setDescricao(request.getParameter("descricao"));
		produto.setValor(request.getParameter("valor"));
		produto.setTamanho(request.getParameter("tamanho"));
		produto.setCor(request.getParameter("cor"));
	
		dao.inserirProduto(produto);
	
		response.sendRedirect("main");

	}

	protected void listarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idcon = request.getParameter("idcon");

		produto.setIdcon(idcon);
		
		dao.selecionarProduto(produto);
		
		request.setAttribute("idcon", produto.getIdcon());
		request.setAttribute("nome", produto.getNome());
		request.setAttribute("descricao", produto.getDescricao());
		request.setAttribute("valor", produto.getValor());
		request.setAttribute("tamanho", produto.getTamanho());
		request.setAttribute("cor", produto.getCor());
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}


	protected void removerProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idcon = request.getParameter("idcon");
		
		produto.setIdcon(idcon);
		
		dao.deletarProduto(produto);

		response.sendRedirect("main");
	}
	

	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
		
			response.setContentType("apllication/pdf");

			response.addHeader("Content-Disposition", "inline; filename="
					+ "Produtos.pdf");
	
			PdfWriter.getInstance(documento, response.getOutputStream());
		
			documento.open();
			documento.add(new Paragraph("Lista de produtos: "));
			documento.add(new Paragraph(""));
			documento.add(new Paragraph(""));
		
			PdfPTable tabela = new PdfPTable(5);
	
			PdfPCell col1 = new PdfPCell (new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell (new Paragraph("Descricao"));
			PdfPCell col3 = new PdfPCell (new Paragraph("Valor"));
			PdfPCell col4 = new PdfPCell (new Paragraph("Tamanho"));
			PdfPCell col5 = new PdfPCell (new Paragraph("Cor"));
			
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			tabela.addCell(col5);
	
			ArrayList<JavaBeans> lista = dao.listarProdutos();
			for (int i= 0;i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getDescricao());
				tabela.addCell(lista.get(i).getValor());
				tabela.addCell(lista.get(i).getTamanho());
				tabela.addCell(lista.get(i).getCor());
			}
			
			documento.add(tabela);
			documento.close();
			
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
		
	}
}

