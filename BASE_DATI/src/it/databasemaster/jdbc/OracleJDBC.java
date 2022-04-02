package it.databasemaster.jdbc;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class OracleJDBC {
	public static void main(String[] args) {
		System.out.println("-------- Test della connessione a Oracle con JDBC ------");
 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Oracle JDBC non trovato");
			e.printStackTrace();
			return;
		}
 
		System.out.println("Driver Oracle JDBC trovato!");
		connection = null;
 
		try {
			connection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/progetto?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "progetto","progetto");
		} catch (SQLException e) {
			System.out.println("Connessione fallita! Guarda lo stacktrace sulla console");
			e.printStackTrace();
			return;
		}
 
		if (connection != null) {
			System.out.println("Conessione stabilita con successo!");
		} else {
			System.out.println("Impossibile stabilire la connessione!");
		}
		
		System.out.println("Scegli l'operazione");
		
		System.out.println("1.Stampa Responsabili");
		System.out.println("2.Stampa la classifica delle Squadre");
		System.out.println("3.Stampa i Piloti che non hanno mai vinto (Posizione: 1) una gara");
		System.out.println("4.Stampa la classifica dei Piloti");
		System.out.println("5.Stampa nome, numero e punti dei Piloti ordinati per Squadra");
		System.out.println("6.Stampa la media punti di un certo Pilota");
		System.out.println("7.Inserisci Tecnico");
		System.out.println("8.Inserisci vittoria Pilota");
		System.out.println("9.Inserisci Responsabile");
		System.out.println("10.Inserisci Pilota");
		System.out.println("11.Stampa Piloti");
		System.out.println("12.Stampa Tecnici");
		System.out.println("13.Stampa Responsabili di una data nazione");
		System.out.println("14.Stampa Gare che usano un certo tipo di gomme");
		System.out.println("15.Stampa lo Staff completo di una Squadra");
		System.out.println("16.Stampa la media punti Squadra per gara");
		System.out.println("17.Inserisci Gara");
		System.out.println("18.Inserisci Squadra");
		System.out.println("19.Stampa Squadre formatesi dopo un certo anno");
		System.out.println("20.Stampa i Piloti nati dopo un certo anno");
		System.out.println("21.Effettua test");
		System.out.println("0 per uscire");
		
		Scanner in = new Scanner(System.in);
		int i = in.nextInt();
		
		while(i!=0) {
			if(i==1)
				stampaResponsabili();
			else if(i==2)
				classificaSquadra();
			else if(i==3)
				pilotiMaiPrimi();
			else if(i==4)
				classificaPiloti();
			else if(i==5)
				pilotiPerSquadra();
			else if(i==6)
				mediaPuntiPilota();
			else if(i==10)
				insertPilota();
			else if(i==7)
				insertTecnico();
			else if(i==9)
				insertResponsabile();
			else if(i==13)
				responsabiliNazione();
			else if(i==8)
				insertAPunti();
			else if(i==20)
				pilotaNatoDopoAnno();
			else if(i==11)
				stampaPiloti();
			else if(i==12)
				stampaTecnici();
			else if(i==14)
				gareDataGomma();
			else if(i==15)
				stampaStaff();
			else if(i==16)
				mediaPuntiSquadra();
			else if(i==17)
				insertGara();
			else if(i==18)
				insertSquadra();
			else if(i==19)
				squadreFormatesiDopoAnno();
			else if(i==21)
				test();
			System.out.println("Riscegli");
			i = in.nextInt();
		}
	}
	public static void stampaResponsabili() {
		try {
			System.out.println("Stampa Responsabili:");
			myStmt = connection.createStatement();
			String query = "SELECT * " + 
					"FROM responsabile ;";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Nome = "+result.getString("Nome")+", Cognome = "+result.getString("cognome"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void classificaSquadra() {
		try {
			System.out.println("Stampa la classifica delle Squadre:");
			myStmt = connection.createStatement();
			String query = "SELECT s.nome, s.Punti_Squadra FROM Squadra as s ORDER BY s.Punti_Squadra DESC;";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Nome:"+result.getString("Nome")+", punti: "+result.getString("Punti_Squadra"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pilotiMaiPrimi() {
		try {
			System.out.println("Stampa i Piloti che non hanno mai vinto (Posizione: 1) una gara:");
			myStmt = connection.createStatement();
			String query = "SELECT p.nome, p.cognome FROM Pilota AS p WHERE NOT EXISTS("
					+ "SELECT * FROM Gara AS g, a_punti AS a WHERE a.NomeGara = g.Nome AND a.IDPilota = p.ID AND a.Posizione = 1);";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Nome:"+result.getString("Nome")+" "+result.getString("Cognome"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void classificaPiloti() {
		try {
			System.out.println("Stampa la classifica dei Piloti");
			myStmt = connection.createStatement();
			String query = "SELECT p.Nome, p.Cognome, p.Punti_Pilota FROM Pilota AS p ORDER BY p.Punti_Pilota DESC;";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Nome:"+result.getString("Nome")+" "+result.getString("Cognome")+", punti="+result.getString("Punti_Pilota"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pilotiPerSquadra() {
		try {
			System.out.println("Stampa nome, numero e punti dei Piloti ordinati per Squadra");
			myStmt = connection.createStatement();
			String query = "SELECT s.Nome, p.Nome AS NomePilota, p.Cognome, p.Numero, p.Punti_Pilota "
					+ "FROM Pilota AS p, Squadra AS s "
					+ "WHERE p.NomeSquadra = s.Nome "
					+ "GROUP BY s.Nome, p.Nome;";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Nome Squadra: "+result.getString("Nome")+", Nome Pilota:"+result.getString("NomePilota")+" "+result.getString("Cognome")+", numero="+result.getString("Numero")+", punti="+result.getString("Punti_Pilota"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void mediaPuntiPilota() {
		try {
			System.out.println("Stampa la media punti di un certo Pilota");
			myStmt = connection.createStatement();
			System.out.println("Inserisci nome pilota");
			Scanner in = new Scanner(System.in);
			String nome = in.next();
			String query = "SELECT p.nome, AVG(a.punti) as Media_Punti"
					+ " FROM pilota as p, a_punti as a "
					+ "WHERE '"+nome+"'"+" IN"
					+ " (SELECT p.Nome AS Somma "
					+ "WHERE a.IDPilota=p.ID GROUP BY p.Nome);";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Nome Pilota:"+result.getString("Nome")+", Media="+result.getString("Media_Punti"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertPilota() {
		try {
			System.out.println("Inserisci un Pilota");
			myStmt = connection.createStatement();
			Scanner in = new Scanner(System.in);
			Scanner in2 = new Scanner(System.in);
			Scanner in3 = new Scanner(System.in);
			Scanner in4 = new Scanner(System.in);
			System.out.println("Inserisci id");
			String id = in.next();
			System.out.println("Inserisci nome pilota");
			String nome = in3.nextLine();
			System.out.println("Cognome pilota");
			String cognome = in4.nextLine();
			System.out.println("Nazionalità ");
			String naz = in.next();
			System.out.println("Numero pilota");
			String n = in.next();
			int num = Integer.parseInt(n);
			System.out.println("Nome squadra");
			String squadra = in2.nextLine();
			System.out.println("Data di nascita (YYYY-MM-AA)");
			String data = in.next(); 
			String query = "INSERT INTO Pilota "
					+ "VALUES('"+nome+"','"+cognome+"','"+naz+"','"+id+"','"+data+"','"+num+"','"+squadra+"',+NULL)";
			myStmt.executeUpdate(query);
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertTecnico() {
		try {
			System.out.println("Inserisci un Tecnico");
			myStmt = connection.createStatement();
			Scanner in = new Scanner(System.in);
			Scanner in2 = new Scanner(System.in);
			Scanner in3 = new Scanner(System.in);
			Scanner in4 = new Scanner(System.in);
			System.out.println("Inserisci id");
			String id = in.next();
			System.out.println("Inserisci nome tecnico");
			String nome = in3.nextLine();
			System.out.println("Cognome tecnico");
			String cognome = in4.nextLine();
			System.out.println("Mansione ");
			String man = in.next();
			System.out.println("Nome squadra");
			String squadra = in2.nextLine();
			System.out.println("Data di nascita (YYYY-MM-AA)");
			String data = in.next(); 
			String query = "INSERT INTO Tecnico "
					+ "VALUES('"+nome+"','"+cognome+"','"+man+"','"+id+"','"+data+"','"+squadra+"')";
			myStmt.executeUpdate(query);
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertResponsabile() {
		try {
			System.out.println("Inserisci un Responsabile");
			myStmt = connection.createStatement();
			Scanner in = new Scanner(System.in);
			Scanner in2 = new Scanner(System.in);
			Scanner in3 = new Scanner(System.in);
			Scanner in4 = new Scanner(System.in);
			System.out.println("Inserisci id");
			String id = in.next();
			System.out.println("Inserisci nome responsabile");
			String nome = in3.nextLine();
			System.out.println("Cognome responsabile");
			String cognome = in4.nextLine();
			System.out.println("Nazionalità ");
			String naz = in.next();
			System.out.println("Nome squadra");
			String squadra = in2.nextLine();
			System.out.println("Data di nascita (YYYY-MM-AA)");
			String data = in.next(); 
			String query = "INSERT INTO Responsabile "
					+ "VALUES('"+nome+"','"+cognome+"','"+naz+"','"+id+"','"+data+"','"+squadra+"')";
			myStmt.executeUpdate(query);
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void responsabiliNazione() {
		try {
			System.out.println("Stampa Responsabili di una data nazione");
			myStmt = connection.createStatement();
			System.out.println("Inserisci nazione");
			Scanner in = new Scanner(System.in);
			String naz = in.next();
			String query = "SELECT nome"
					+ " FROM responsabile "
					+ "WHERE nazione = '"+naz+"';";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Nome Responsabile:"+result.getString("Nome"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertAPunti() {
		try {
			System.out.println("Inserisci vittoria Pilota");
			myStmt = connection.createStatement();
			Scanner in = new Scanner(System.in);
			System.out.println("Inserisci nome gara");
			String gara = in.nextLine();
			System.out.println("Inserisci id pilota");
			String id = in.next();
			System.out.println("Posizione di arrivo");
			String p = in.next();
			int posizione = Integer.parseInt(p);
			System.out.println("Punti");
			String p2 = in.next();
			int punti = Integer.parseInt(p2);
			String query = "INSERT INTO a_punti "
					+ "VALUES('"+gara+"','"+id+"','"+punti+"','"+posizione+"')";
			myStmt.executeUpdate(query);
			System.out.println("Update in corso dei punti pilota e punti squadra");
			String up1 = "UPDATE Pilota " + 
					"SET Pilota.Punti_Pilota = (SELECT SUM(a.Punti) " + 
					"FROM a_punti AS a " + 
					"WHERE Pilota.ID = a.IDPilota);";
			String up2 = "UPDATE Squadra " + 
					"SET Squadra.Punti_Squadra = (SELECT SUM(p.Punti_Pilota) " + 
					"FROM Pilota AS p " + 
					"WHERE (SELECT p.Punti_Pilota " + 
					"WHERE Squadra.Nome = p.NomeSquadra));";
			myStmt.executeUpdate(up1);
			myStmt.executeUpdate(up2);
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pilotaNatoDopoAnno() {
		try {
			System.out.println("Stampa i Piloti nati dopo un certo anno");
			myStmt = connection.createStatement();
			System.out.println("Inserisci anno");
			Scanner in = new Scanner(System.in);
			String data = in.next()+"-00-00";
			String query = "SELECT nome"
					+ " FROM pilota "
					+ "WHERE datan >= '"+data+"';";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Nome: "+result.getString("Nome"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void stampaPiloti() {
		try {
			System.out.println("Stampa Piloti");
			myStmt = connection.createStatement();
			String query = "SELECT nome, cognome FROM pilota";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Pilota: "+result.getString("Nome")+" "+result.getString("Cognome"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void stampaTecnici() {
		try {
			System.out.println("Stampa Tecnici");
			myStmt = connection.createStatement();
			String query = "SELECT nome, cognome FROM tecnico";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Tecnico: "+result.getString("Nome")+" "+result.getString("Cognome"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void gareDataGomma() {
		try {
			System.out.println("Stampa Gare che usano un certo tipo di gomme");
			myStmt = connection.createStatement();
			Scanner in = new Scanner(System.in);
			System.out.println("Tipo di gomma:");
			String gomma = in.next();
			String query = "SELECT g.nome " + 
					"FROM gara as g, gomme as go " + 
					"WHERE g.nome=go.nome and go.tipo='"+gomma+"';";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Gara: "+result.getString("Nome"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void stampaStaff() {
		try {
			System.out.println("Stampa lo Staff completo di una Squadra");
			myStmt = connection.createStatement();
			Scanner in = new Scanner(System.in);
			System.out.println("Nome Squadra:");
			String sq = in.nextLine();
			String query = "(select p.id, p.nome, p.cognome from pilota as p where p.nomesquadra = '"+sq+"') " + 
					"union" + 
					" (select t.id, t.nome, t.cognome from tecnico as t where t.nomesquadra = '"+sq+"')" + 
					" union" + 
					" (select r.id, r.nome, r.cognome from responsabile as r where r.nomesquadra = '"+sq+"');";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next()) {
				System.out.println("ID: "+result.getString("ID")+", Nome: "+result.getString("Nome")+", Cognome: "+result.getString("Cognome"));
			}
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void mediaPuntiSquadra() {
		try {
			System.out.println("Stampa la media punti Squadra per gara");
			myStmt = connection.createStatement();
			System.out.println("Inserisci nome squadra");
			Scanner in = new Scanner(System.in);
			String sq = in.nextLine();
			String query = "select g.nome, avg(a.punti) as media "
					+ "from a_punti as a, pilota as p, gara as g "
					+ "where p.id = a.idpilota and p.nomesquadra = '"+sq+"' and a.nomegara = g.nome group by g.nome;";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Nome squadra:"+result.getString("Nome")+", Punteggio Medio="+result.getString("media"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertGara() {
		try {
			System.out.println("Inserisci Gara");
			myStmt = connection.createStatement();
			Scanner in = new Scanner(System.in);
			System.out.println("Inserisci nome");
			String nome = in.nextLine();
			System.out.println("Inserisci citta");
			String citta = in.nextLine();
			System.out.println("Nazione ");
			String naz = in.nextLine();
			System.out.println("Numero giri");
			String g = in.next();
			int giri = Integer.parseInt(g);
			String query = "INSERT INTO gara "
					+ "VALUES('"+nome+"','"+citta+"','"+naz+"','"+giri+"')";
			myStmt.executeUpdate(query);
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertSquadra() {
		try {
			System.out.println("Inserisci Squadra");
			myStmt = connection.createStatement();
			Scanner in = new Scanner(System.in);
			System.out.println("Inserisci nome");
			String nome = in.nextLine();
			System.out.println("Anno Formazione");
			String a = in.next();
			int anno = Integer.parseInt(a);
			String query = "INSERT INTO Squadra "
					+ "VALUES('"+nome+"','"+anno+"',0)";
			myStmt.executeUpdate(query);
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void squadreFormatesiDopoAnno() {
		try {
			System.out.println("Stampa Squadre formatesi dopo un certo anno");
			myStmt = connection.createStatement();
			Scanner in = new Scanner(System.in);
			System.out.println("Anno Formazione");
			String a = in.next();
			int anno = Integer.parseInt(a);
			String query = "SELECT s.nome " + 
					"FROM squadra as s " + 
					"WHERE s.annoformazione >= "+anno+";";
			ResultSet result = myStmt.executeQuery(query);
			while(result.next())
				System.out.println("Nome squadra:"+result.getString("Nome"));
			System.out.println("=========================================================");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test(){
		int i=1;
		Scanner in = new Scanner(System.in);
		while(i<21) {
			if(i==1)
				insertGara();
			else if(i==2)
				insertSquadra();
			else if(i==3) {
				insertPilota();
				insertPilota();
			}
			else if(i==4)
				insertTecnico();
			else if(i==5)
				insertResponsabile();
			else if(i==6)
				insertAPunti();
			else if(i==7)
				classificaPiloti();
			else if(i==8)
				classificaSquadra();
			else if(i==9)
				stampaPiloti();
			else if(i==10)
				stampaTecnici();
			else if(i==11)
				responsabiliNazione();
			else if(i==12)
				stampaStaff();
			else if(i==13)
				pilotiMaiPrimi();
			else if(i==14)
				pilotiPerSquadra();
			else if(i==15)
				mediaPuntiPilota();
			else if(i==16)
				mediaPuntiSquadra();
			else if(i==17)
				squadreFormatesiDopoAnno();
			else if(i==18)
				gareDataGomma();
			else if(i==19)
				pilotaNatoDopoAnno();
			else if(i==20)
				stampaResponsabili();
			in.next();
			i++;
		}
	}
	
	private static Statement myStmt;
	private static Connection connection;
}