package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Telefoneuser;
import model.Userpostegres;

public class UserPostegresDAO {
	
	private Connection connection; //Variável que recebe a conexão.
	
	public UserPostegresDAO() {//Construtor que cria a conexão.
		connection = SingleConnection.getConection(); //Conexão instâciada.
	}
	
	public void salvar(Userpostegres obj) { // Vai salvar um registro recebendo um obj por parâmetro.
		try {
		String sql = "INSERT INTO userpostegres (nome, email) VALUES(?,?)"; // String que representa o SQL.
		PreparedStatement insert = connection.prepareStatement(sql); //Prepara a instrução que antes é uma String.
		insert.setString(1, obj.getNome());
		insert.setString(2, obj.getEmail());
		
		insert.execute();
		connection.commit(); //Salva no banco de dados.
		
		}catch(Exception e) {
			
			try {
				connection.rollback();//Se algo der errado ele execulta um rollback que desfaz a operação de commit.
			
			}catch(Exception ex){
				
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void salvarTelefone(Telefoneuser obj) {//Método será usando pra salvar o telefone..
		
		try {
			
			String sql = "INSERT INTO telefoneuser(numero, tipo, userpessoa) VALUES (?, ?, ?)";
			
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, obj.getNumero());
			pst.setString(2,obj.getTipo());
			pst.setLong(3, obj.getUserPostegres());
			
			pst.execute();
			connection.commit();
			
		}catch(Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public List<Userpostegres> listar() throws Exception{
		List<Userpostegres> list = new ArrayList<Userpostegres>();
		String sql = "SELECT * FROM userpostegres";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();// recebe uma lista.
		
		while(resultado.next()){//Enquanto o resultado existir:
			
			Userpostegres obj = new Userpostegres();
			
			obj.setId(resultado.getLong("id")); //Vou colocar os nomes das coluna para referênciar no resultado
			obj.setNome(resultado.getString("nome"));
			obj.setEmail(resultado.getString("email"));
			
			list.add(obj);// Adiciono na Lista.
		}
		
		return list;

	}
	
	public Userpostegres buscar(Long id) throws Exception{
		Userpostegres retorno = new Userpostegres();
		String sql = "SELECT * FROM userpostegres WHERE id = " + id; 
//      Será retornado o registro com ID igual ao passado por parâmetro
		

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();// recebe uma lista.
		
		while(resultado.next()){//Apenas um ou nenhum
			
			retorno.setId(resultado.getLong("id"));//Vou colocar os nomes das coluna para referênciar no resultado
		    retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}
		
		return retorno;

	}
	
	public void atualizar(Userpostegres obj) {
		try {
			
		String sql = "UPDATE userpostegres SET nome = ? WHERE id =" + obj.getId();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, obj.getNome());
		statement.execute();
		connection.commit();
		
		}catch(Exception x) {
			
			try {
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x.printStackTrace();
		}
	
	}
	
	public void deletar(Long id)  {
		try {
			
			String sql = "DELETE FROM userpostegres WHERE id =" + id;
			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.execute();
			
			connection.commit();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
