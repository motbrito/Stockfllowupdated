package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbstrockflow?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "mSVT040905*";

	
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void TesteConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	public void inserirProduto(JavaBeans produto) {
		String create = "insert into produtos (nome,descricao,valor,tamanho,cor) values (?,?,?,?,?)";
		try {
		
			Connection con = conectar();
			
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getDescricao());
			pst.setString(3, produto.getValor());
			pst.setString(4, produto.getTamanho());
			pst.setString(5, produto.getCor());
			
			pst.executeUpdate();
		
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	public ArrayList<JavaBeans> listarProdutos() {
		
		ArrayList<JavaBeans> produtos = new ArrayList<>();
		String read = "select * from produtos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
		
			while (rs.next()) {
				
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String descricao = rs.getString(3);
				String valor = rs.getString(4);
				String tamanho = rs.getString(5);
				String cor = rs.getString(6);
			
				produtos.add(new JavaBeans(idcon, nome, descricao, valor,tamanho,cor));
			}
			con.close();
			return produtos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	public void selecionarProduto(JavaBeans produto) {
		String read2 = "select * from produtos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, produto.getIdcon());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				produto.setIdcon(rs.getString(1));
				produto.setNome(rs.getString(2));
				produto.setDescricao(rs.getString(3));
				produto.setValor(rs.getString(4));
				produto.setTamanho(rs.getString(5));
				produto.setCor(rs.getString(5));
						}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	
		public void deletarProduto(JavaBeans produto) {
			String delete = "delete from produtos where idcon=?";
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, produto.getIdcon());
				pst.executeUpdate();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
}