package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/postgres"; //Define a url do banco.
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null; //Variável de conexão.
	
	static { // Sempre que eu envocar esse SingleConnection vou chamar o conectar().
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if(connection == null) { //Se a conexão ainda não existe.
				Class.forName("org.postgresql.Driver"); //Carregando o driver.
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);//Define que as operações de banco de dados não serão salvos automáticamente.
				System.out.println("Conectou com sucesso!");
			}
			
		}catch(Exception e) {
			e.printStackTrace(); //Mostra na tela caso aconteça um erro.
		}
	
	}
	
	public static Connection getConection() {// Método retorna uma coneção.
		return connection;
	}

}
