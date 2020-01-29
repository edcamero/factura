package Conexion;


import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Conexion{
	private static Conexion conexion;
	private  String usuario="postgres", password="1234", iP="localhost", puerto="5433", nombreBD="gnosoft";
        private Connection con;
        private Statement sentencia;

	private Conexion(){
            
	}
        
        public static Conexion getConexion(){
            if(conexion==null){
                conexion=new Conexion();
            }
            return conexion;
        }
	public Conexion(String iP,String puerto,String nombreBD,String usuario,String password){
		this.usuario = usuario;
		this.password = password;
		this.iP = iP;
		this.puerto = puerto;
		this.nombreBD = nombreBD;
                
	}

	// sw es true si va a conectar con MySQL (3306) y postgresql (5432)
	// sw es false si va a conectar con access porque es por medio del odbc
	private void conectar	(String driver, String puente, boolean sw) 
					throws ClassNotFoundException,
						SQLException,
						InstantiationException, 
						IllegalAccessException{

		Class.forName(driver).newInstance();
                
		if (sw)
 			con = DriverManager.getConnection ("jdbc:"+puente+
								"://"+iP+
								":"+puerto+
								"/"+nombreBD,usuario,password);
		else
			con = DriverManager.getConnection ("jdbc:"+puente+
								":"+nombreBD,usuario,password);
		sentencia = con.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,
								    ResultSet.CONCUR_UPDATABLE);
	}

	// Con JDBC
	public void ConexionJDBC() throws 	ClassNotFoundException, 
							SQLException,
							InstantiationException,
							IllegalAccessException{
		conectar("sun.jdbc.odbc.JdbcOdbcDriver","odbc",false);
	}

	// Con MySQL
	public void ConexionMySQL() throws   ClassNotFoundException,
							SQLException,
							InstantiationException,
							IllegalAccessException{
		conectar("com.mysql.jdbc.Driver","mysql",true);
	}

	// Con PostgreSql
	public void ConexionPostgres() throws ClassNotFoundException,
							SQLException,
							InstantiationException,
							IllegalAccessException{
			conectar("org.postgresql.Driver","postgresql",true);
	}

	public void ConexionOracle(String driver, String cadena_conexion, String usuario, String clave) throws ClassNotFoundException,
							SQLException,
							InstantiationException,
							IllegalAccessException
	
	{
	  //Class.forName("oracle.jdbc.driver.OracleDriver");

      //conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521", "provision", "provision");
    
    Class.forName(driver);
    con = DriverManager.getConnection(cadena_conexion,usuario,clave);
	sentencia = con.createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,
								    ResultSet.CONCUR_UPDATABLE);

//    Statement st=conexion.createStatement();              
	}

	
	
	public void actualizar(String actualiza) throws SQLException{
		sentencia.executeUpdate(actualiza);
	}

	public int actualizar1(String actualiza) throws SQLException{
		int s= sentencia.executeUpdate(actualiza);
	   
	   return s;
	}

	public ResultSet consultar(String consulta) throws SQLException{
		return (sentencia.executeQuery(consulta));
	}

        public ResultSet consulta(String consulta){
      
      ResultSet res=null;
      
      try {
          res=sentencia.executeQuery(consulta);
          
      } catch (SQLException ex) {
           System.err.print("Error en introducion comando para consulta");
      }
      
      return res;
      
  }
  public DefaultTableModel consultaG(String query)
   {    DefaultTableModel modelo=null;
       try {       
           ConexionPostgres();
           String matriz[][] = {};
           ResultSet resultado=consulta(query);
           ResultSetMetaData meta=resultado.getMetaData();
           String[] col = new String[meta.getColumnCount()];
           for(int i=1;i<=col.length;i++){
               col[i-1]=resultado.getMetaData().getColumnName(i);
              }
           modelo=new DefaultTableModel(matriz,col);
           int conta=0;
          while(resultado.next())
           {
            modelo.insertRow(conta,new Object[]{});
            
            for(int i=1;i<=col.length;i++)
            {
             modelo.setValueAt(resultado.getString(resultado.getMetaData().getColumnName(i)), conta, i-1);    
            
            }
            conta++;
           }  
          sentencia.close();
      } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
          Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
      
       }
      return modelo;    
   }
  
	public int contar(ResultSet rS) throws SQLException{
		int cont = 0;
		rS.beforeFirst();
		while (rS.next()) cont++;
		return (cont);
	}

	public void cerrar() throws SQLException{
		con.close();
		sentencia.close();
	}

    public Connection getCon() {
        return con;
    }
    
 
        
}
