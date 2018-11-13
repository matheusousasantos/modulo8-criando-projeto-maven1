package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPostegresDAO;
import model.BeanUserFone;
import model.Telefoneuser;
import model.Userpostegres;

public class TesteBancoJdbc {
	
	@Test //Anotação d teste
	public void initBanco() { //Inicializa o banco.
		
		UserPostegresDAO userPostegresDAO = new UserPostegresDAO(); //Criando um dao junto com a conexão
		Userpostegres userPostegres = new Userpostegres(); //Criando um novo obj
		
		userPostegres.setNome("Matheus");
		userPostegres.setEmail("matheus@gmail.com");
		
		userPostegresDAO.salvar(userPostegres);
		
	}
	
	@Test
	public void initListar() {
		
		UserPostegresDAO dao = new UserPostegresDAO();
		List<Userpostegres> list;
		
		try {
			list = dao.listar();
			for(Userpostegres x : list) {
				System.out.println(x);
				System.out.println("==================================================================");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initBuscar() {
		
		UserPostegresDAO dao = new UserPostegresDAO();
		
		try {
			
			Userpostegres obj = dao.buscar(6L);
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initAtualizar() {
		
		UserPostegresDAO dao = new UserPostegresDAO();
		try {
			Userpostegres obj = dao.buscar(6L);
			obj.setNome("Mome mudado com método atualizar");
			dao.atualizar(obj);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initDeletar() {
		try {
			
			UserPostegresDAO dao = new UserPostegresDAO();
			dao.deletar(7L);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeInsertTelefone() {
		
		Telefoneuser obj = new Telefoneuser();
		obj.setNumero("9894989889");
		obj.setTipo("casa");
		obj.setUserPostegres(2L);
		
		UserPostegresDAO dao = new UserPostegresDAO();
		
		dao.salvarTelefone(obj);
		
	}
	
	@Test
	public void testeCarregarFonesUser() {
		UserPostegresDAO dao = new UserPostegresDAO();
		
		List<BeanUserFone> objs  = dao.listarUserFone(1L);
		
		for(BeanUserFone x : objs) {
			System.out.println(x.toString());
			System.out.println("--------------------");
		}
		
	}

}
