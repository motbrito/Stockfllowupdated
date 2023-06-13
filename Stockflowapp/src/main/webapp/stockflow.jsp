<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList <JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("produtos");
	
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Stockflow</title>
<link rel="icon" href="Imagens/favicon.png">
<link rel="stylesheet" href="index.css">
</head>
<body>
	<div class = "box">
		<img src = Imagens/STOCKFLOWon.png>
		
		<div class = "boxx">
			<a href="novo.html" class="Botao1"> Novo produto </a>
			<a href="report" class="Botao1"> Relatório</a>
		</div>

	</div>

	<table id="tabela">
		<thead>
			<tr> 
				<th>ID</th>
				<th>Nome</th>
				<th>Descrição</th>
				<th>Valor</th>
				<th>Tamanho</th>
				<th>Cor</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%for (int i = 0; i < lista.size(); i++) { %>
				<tr> 
					<td> <%=lista.get(i).getIdcon()%></td>
					<td> <%=lista.get(i).getNome()%></td>
					<td> <%=lista.get(i).getDescricao()%></td>
					<td> <%=lista.get(i).getValor()%></td>
					<td> <%=lista.get(i).getTamanho()%></td>
					<td> <%=lista.get(i).getCor()%></td>		
			
					<td> 
					<a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" class=Botao2>Excluir</a>
					</td>
				</tr>
			<%} %>
		</tbody>
	</table>
	<script src="Scripts/confirmador.js"></script>
</body>
</html>