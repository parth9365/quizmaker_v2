package com.quiz.commons;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.dbcp.BasicDataSource;

public class Query {

  	private static String connectionString	=	null;
	private static String driverString		=	null;
	private static String userName			=	null;
	private static String userPassword		=	null;

	public static volatile boolean DEBUG=false;//for live servers
	PrintStream fout;

	private static BasicDataSource basicDataSource = null;

    public Query() throws Exception {
		
		try {
			new ProjectConfig(); //to initlise the log file in the constructer of projectConfig
//			String outfile = "/home/newuser/log/Log" +'_'+ new java.util.Date().toString().replace(' ','_').replace(':','-') + ".txt";
//	
//			fout=new PrintStream(new FileOutputStream(outfile, true));
//			System.setOut(fout);
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw e;
		}
		
		connectionString = ProjectConfig.get("connectionString");
		driverString = ProjectConfig.get("driverString");
		userName = ProjectConfig.get("userName");
		userPassword = ProjectConfig.get("userPassword");
	
		createDataSource();
	}

    /**
     * 
     * @param flag
     * @throws Exception
     */
    public Query(boolean flag) throws Exception{
		this(flag,0);
	}
    
    
    public Query(boolean flag, int numConnections) throws Exception{

		try{
			new ProjectConfig(); //to initlise the log file in the constructer of projectConfig
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw e;
		}
    	createDataSource();
	}

    
    /**
     * 
     * @param query
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws DataBaseException
     * @throws Exception
     */
	public static DbResultset executeSelect(String query) throws ClassNotFoundException,SQLException,Exception {
		if(DEBUG)
			System.out.println("DB-MSG:ES: query="+query);
		
		return executeSelect("",query,0,0,false);
	}

	
	/**
	 * 
	 * @param query
	 * @param duration
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DataBaseException
	 * @throws Exception
	 */
	public static DbResultset executeSelect(String query,int duration)throws ClassNotFoundException,SQLException,Exception{
		if(DEBUG)
			System.out.println("DB-MSG:ES: query="+query +"Extra Time(in ms) : "+ duration);
		
		return executeSelect("",query,0,0,false,duration);
	}


/*******************TO BE USED IN CASE OF SEARCH  USING A JOIN MULTIPLE **************/

	public static DbResultset executeSelectForJoins(String columns,String querySuffix,int listPageSize,int pageNumber,boolean isCountNeeded) throws ClassNotFoundException,SQLException,Exception {
		return executeSelect(columns, querySuffix, listPageSize, pageNumber, isCountNeeded);
	}

	public static DbResultset executeSelect(String columns,String querySuffix,int listPageSize,int pageNumber,boolean isCountNeeded) throws ClassNotFoundException,SQLException,Exception {
		return executeSelect(columns,querySuffix,listPageSize,pageNumber,isCountNeeded,0);
	}

	public static DbResultset executeSelect(String columns,String querySuffix,int listPageSize,int pageNumber,boolean isCountNeeded,int duration) throws ClassNotFoundException,SQLException,Exception {

		DbResultset dbResult = null;
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet rs = null;

		long starttime = new java.util.Date().getTime();

		try{
			int totalRows=-1;
			String query="";
			
			//THIS PART FETCHES NUMBER OF ROWS
			if(!columns.equals("") && !(querySuffix.toLowerCase()).trim().startsWith("from")) 
				throw new Exception("DbBean:Invalid Query Suffix (doesn't start with from)");

			if(isCountNeeded){
	  			/*
	  			 commented by Uday 31 Aug 2012	
				 connection = ConnectionPoolReadOnly.getConnection(duration);				
				*/
				
	  			connection = basicDataSource.getConnection();
				int index=querySuffix.lastIndexOf("order by");
				if (index==-1) index=querySuffix.length();
				String querySuffixCount=querySuffix.substring(0,index);
				query= "Select count(1) TotalRows " + querySuffixCount;
				
				stmt = connection.prepareStatement(query);
				rs = stmt.executeQuery();
				rs.next();
				totalRows=rs.getInt("TotalRows");
				
				/* commented by Uday 31 Aug 2012
				closeConnectionReadOnly(connection,stmt,rs);
				*/
				closeConnectionDbcp(connection, stmt, rs);
				connection = null; stmt = null; rs= null;
  			}

			//THIS PART GETS ACTUAL DATA

	  		/* commented by Uday 31 Aug 2012
			connection= ConnectionPoolReadOnly.getConnection(duration);
			*/
			connection = basicDataSource.getConnection();
			String selectClause = "";
			
			if(listPageSize > 0) 
				selectClause = "Select Top " + (listPageSize * pageNumber) + " ";
			
			query = selectClause + columns + querySuffix;
			stmt = connection.prepareStatement(query);

			rs = stmt.executeQuery();
			
            if(DEBUG)
            	System.out.println("rs ******** class   "+rs.getClass());

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			Hashtable columnNames = new Hashtable();
			
			for(int i=1;i<=columnCount;i++){
				columnNames.put(rsmd.getColumnName(i),new Integer(i-1));
			}

			Vector rowWiseData = new Vector(listPageSize,10);

			try{
				for(int k=0;k<listPageSize*(pageNumber-1);k++){
					rs.next();
				}
			}catch(Exception e){
				System.out.println(""+e);
			}
			
			while(rs.next()) {
				
				Vector tempDataVector = new Vector(columnCount);
				
				for(int i=1; i<=columnCount; i++){

					String resultData = rs.getString(i);

					if(resultData== null){
						resultData="";
					}
					tempDataVector.addElement(resultData);
				}
				rowWiseData.addElement(tempDataVector);
			}


			if(!rowWiseData.isEmpty()){
				dbResult=new DbResultset(columnNames, rowWiseData,totalRows);
			}else
				dbResult = new DbResultset();
			
			java.util.Date currentTime=new java.util.Date();
			long exectime = (new java.util.Date().getTime() - starttime);
		  	if(DEBUG || exectime > 5000 ) System.out.println("DB-MSG:ES3: Currenttime :"+currentTime+" Exectime: "+exectime+" millisecs ARGS - /Cols:"+columns+" /Sufx:"+querySuffix+" /pgSize:"+listPageSize+" /pgNum:"+pageNumber+" /Count:"+isCountNeeded);

			return dbResult;
			
			}
		catch(Exception e){
			System.out.println("DB-MSG:ES3: inside executeSelect(String columns,String querySuffix,int listPageSize,int pageNumber,boolean isCountNeeded)..."+columns+querySuffix);
			e.printStackTrace(System.out);
			throw e;
		}finally{
			/* commented by Uday 31 Aug 2012
			closeConnectionReadOnly(connection,stmt,rs);
			*/
			closeConnectionDbcp(connection, stmt, rs);
			//dbResult = null; 
			connection = null; stmt = null; rs=null;
			dbResult = null;
		}
		

	}


//**********************


//**********************

	public static DbResultset executeSelectForUnion(String query,int listPageSize,int pageNumber,boolean isCountNeeded)throws ClassNotFoundException,SQLException,Exception{
		return executeSelectForUnion(query,listPageSize,pageNumber,isCountNeeded,0);
	}


/***************KCB********************/

	
	public static DbResultset executeSelectForUnion(String query,int listPageSize,int pageNumber,boolean isCountNeeded,int duration) throws ClassNotFoundException,SQLException,Exception {

		DbResultset dbResult = null;
		Connection connection=null;
		CallableStatement stmt=null;
		ResultSet rs = null;

		long starttime = new java.util.Date().getTime();

		try{

			int totalRows=-1;
		  	/* commented by Uday 31 Aug 2012
			connection= ConnectionPoolReadOnly.getConnection(duration);
			*/
		  	connection = basicDataSource.getConnection();
			if(DEBUG)
				System.out.println("DB-MSG:ES3: Query2 ****** DBBean 168 "+query);

			int isCountNeededInt=0;
			
			if(isCountNeeded)
				isCountNeededInt=1;
			else 
				isCountNeededInt=0;

			stmt = connection.prepareCall(	"{call sp_executeUnionQuery('"+CommonFuncs.replace(query,"'","''")+"',"+pageNumber+","+listPageSize+","+isCountNeededInt+",?)}");
			stmt.registerOutParameter(1, java.sql.Types.INTEGER);

			rs = stmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();
			Hashtable columnNames = new Hashtable();

			for(int i=1;i<=columnCount;i++){
				columnNames.put(rsmd.getColumnName(i),new Integer(i-1));
			}

			Vector rowWiseData = new Vector(listPageSize,10);

			try{
				for(int k=0;k<listPageSize*(pageNumber-1);k++){
					rs.next();
				}
			}catch(Exception e){
				System.out.println(""+e);
			}
			
			while(rs.next()){
				Vector tempDataVector = new Vector(columnCount);
				
				for(int i=1; i<=columnCount; i++){

					String resultData = rs.getString(i);

					if(resultData== null){
						resultData="";
					}
					
					tempDataVector.addElement(resultData);
				}
				
				rowWiseData.addElement(tempDataVector);
			}

			if(isCountNeeded)
				totalRows=stmt.getInt(1);

			if(!rowWiseData.isEmpty()){
				dbResult=new DbResultset(columnNames, rowWiseData,totalRows);
			}else
				dbResult = new DbResultset();
			
			java.util.Date currentTime=new java.util.Date();
			long exectime = (new java.util.Date().getTime() - starttime);
		  	if(DEBUG || exectime > 5000 ) System.out.println("DB-MSG:ES3: Currenttime :"+currentTime+" Exectime: "+exectime+" millisecs ARGS - /Union query:"+query+" /pgSize:"+listPageSize+" /pgNum:"+pageNumber+" /Count:"+isCountNeeded);
			
		  	return dbResult;
			
		}catch(Exception e){
			System.out.println("DB-MSG:ES3: inside executeSelectForUnion(String query,int listPageSize,int pageNumber,boolean isCountNeeded)..."+query+" /pgSize:"+listPageSize+" /pgNum:"+pageNumber+" /Count:"+isCountNeeded);
			e.printStackTrace(System.out);
			throw e;
		}finally{
			/* commented by Uday 31 Aug 2012
			closeConnectionReadOnly(connection,stmt,rs);
			*/
			closeConnectionDbcp(connection, stmt, rs);
			dbResult = null; connection = null; stmt = null; rs = null;
		}
		//Changed By Mona 9-5-2013
		//java.util.Date currentTime=new java.util.Date();
		//long exectime = (new java.util.Date().getTime() - starttime);
	  	//if(DEBUG || exectime > 5000 ) System.out.println("DB-MSG:ES3: Currenttime :"+currentTime+" Exectime: "+exectime+" millisecs ARGS - /Union query:"+query+" /pgSize:"+listPageSize+" /pgNum:"+pageNumber+" /Count:"+isCountNeeded);

		//return dbResult;
	}
/***************KCB************/


	public  static void executeInsert(String query) throws ClassNotFoundException,SQLException,Exception {
		Connection  connection=null;
		Statement stmt=null;
		long starttime = new java.util.Date().getTime();
		
		try{
			/* commented by Uday 31 Aug 2012
		    connection = ConnectionPool.getConnection();
		    */
			connection = basicDataSource.getConnection();
		    stmt = createStatement(connection);
			stmt.executeUpdate(query);
		}catch(Exception e) {
			System.out.println("DB-MSG:EI: inside executeInsert(String query)..."+query);
			e.printStackTrace(System.out);
			throw e;
		}finally{
			/* commented by Uday 31 Aug 2012
			closeConnection(connection,stmt);
			*/
			closeConnectionDbcp(connection, stmt);
			connection = null; stmt = null;
		}

		java.util.Date currentTime=new java.util.Date();
		long exectime = (new java.util.Date().getTime() - starttime);
	  	if(DEBUG || exectime > 5000 ) System.out.println("DB-MSG:ES3: Currenttime :"+currentTime+" Exectime: "+exectime+" millisecs ARGS - /Query:"+query);
	}
	
	public  static long executeInsert(String query, boolean returnGeneratedKey) throws ClassNotFoundException,SQLException,Exception {
		Connection  connection=null;
		Statement stmt=null;
		
		long starttime = new java.util.Date().getTime();
		long id = -1; 
		try{
			/* commented by Uday 31 Aug 2012
		    connection = ConnectionPool.getConnection();
		    */
			connection = basicDataSource.getConnection();
			stmt = createStatement(connection);
			
			if(returnGeneratedKey)
				stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			else
				stmt.executeUpdate(query);
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next())
		  		id = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("DB-MSG:EI: inside executeInsert(String query)..."+query);
			e.printStackTrace(System.out);
			throw e;
		}finally{
			/* commented by Uday 31 Aug 2012
			closeConnection(connection,stmt);
			*/
			closeConnectionDbcp(connection, stmt);
			connection = null; stmt = null;
		}

		java.util.Date currentTime=new java.util.Date();
		long exectime = (new java.util.Date().getTime() - starttime);
	  	if(DEBUG || exectime > 5000 ) System.out.println("DB-MSG:ES3: Currenttime :"+currentTime+" Exectime: "+exectime+" millisecs ARGS - /Query:"+query);
	  	
	  	
	  	
	  	return id;
	}


	/**
	 * 
	 * @param query
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DataBaseException
	 * @throws Exception
	 */
	public  static void execute(String query) throws ClassNotFoundException,SQLException,Exception {
		Connection  connection=null;
		Statement stmt=null;
		long starttime = new java.util.Date().getTime();
		
		try{
			/* commented by Uday 31 Aut 2012
			connection = ConnectionPool.getConnection();
			*/
  			connection = basicDataSource.getConnection();

		    stmt = createStatement(connection);
			stmt.execute(query);
		}catch(Exception e){
			System.out.println("DB-MSG:E: inside execute(String query)..."+query);
			e.printStackTrace(System.out);
			throw e;
		}finally{
			/* commented by Uday 31 Aug 2012
			closeConnection(connection,stmt);
			*/
			closeConnectionDbcp(connection, stmt);
			stmt = null; connection = null;
		}

		java.util.Date currentTime=new java.util.Date();
		long exectime = (new java.util.Date().getTime() - starttime);
	  	if(DEBUG || exectime > 5000 ) System.out.println("DB-MSG:ES3: Currenttime :"+currentTime+" Exectime: "+exectime+" millisecs ARGS - /Query:"+query);
	}


	/**
	 * 
	 * @param query
	 * @param duration
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DataBaseException
	 * @throws Exception
	 */
	public  static void execute(String query,int duration) throws ClassNotFoundException,SQLException,Exception {
		Connection  connection=null;
		Statement stmt=null;
		long starttime = new java.util.Date().getTime();
		
		try{
			/* commented by Uday 31 Aug 2012
			connection = ConnectionPoolReadOnly.getConnection(duration);
			*/
  			connection = basicDataSource.getConnection();

			stmt = createStatement(connection);
			stmt.execute(query);
		}catch(Exception e){
			System.out.println("DB-MSG:E: inside execute(String query)..."+query);
			e.printStackTrace(System.out);
			throw e;
		}finally{
			/* commented by Uday 31 Aug 2012
			closeConnection(connection,stmt);
			*/
			closeConnectionDbcp(connection, stmt);
			stmt = null; connection = null;
		}

		java.util.Date currentTime=new java.util.Date();
		long exectime = (new java.util.Date().getTime() - starttime);
	  	if(DEBUG || exectime > 5000 ) System.out.println("DB-MSG:ES3: Currenttime :"+currentTime+" Exectime: "+exectime+" millisecs ARGS - /Query:"+query);
	}



	/**
	 * 
	 * @param query
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DataBaseException
	 * @throws Exception
	 */
	public static void executeUpdate(String query) throws ClassNotFoundException,SQLException,Exception {
		Connection  connection=null;
		Statement stmt=null;
		long starttime = new java.util.Date().getTime();

		try{
			/* commented by Uday 31 Aug 2012
			connection = ConnectionPool.getConnection();
			*/
  			connection = basicDataSource.getConnection();

		    stmt = createStatement(connection);
			stmt.executeUpdate(query);
		}catch(Exception e){
			System.out.println("DB-MSG:EU: inside executeUpdate(String query)..."+query);
			e.printStackTrace(System.out);
			throw e;
		}finally{
			/* commented by Uday 31 Aug 2012
			closeConnection(connection,stmt);
			*/
			closeConnectionDbcp(connection, stmt);
			stmt = null; connection = null;
		}

		java.util.Date currentTime=new java.util.Date();
		long exectime = (new java.util.Date().getTime() - starttime);
	  	if(DEBUG || exectime > 5000 ) System.out.println("DB-MSG:ES3: Currenttime :"+currentTime+" Exectime: "+exectime+" millisecs ARGS - /Query:"+query);
	}


	/**
	 * 
	 * @param query
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DataBaseException
	 * @throws Exception
	 */
	public static void executeDelete(String query) throws ClassNotFoundException,SQLException,Exception {
		long starttime = new java.util.Date().getTime();
		
		try{
			executeUpdate(query);
		}catch(Exception e){
			System.out.println("inside executeDelete(String query)...");
			e.printStackTrace(System.out);
			throw e;
		}

		java.util.Date currentTime=new java.util.Date();
		long exectime = (new java.util.Date().getTime() - starttime);
	  	if(DEBUG || exectime > 5000 ) System.out.println("DB-MSG:ES3: Currenttime :"+currentTime+" Exectime: "+exectime+" millisecs ARGS - /Query:"+query);
	}

	
	/**
	 * 
	 * @param connection
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private static Statement createStatement(Connection connection) throws SQLException,Exception {
		Statement stmt =null;
		
		try{
			stmt=connection.createStatement();
		}catch(Exception e){
			System.out.println("DB-MSG:CS:inside createStatement(Connection connection)...");
			e.printStackTrace(System.out);
			throw e;
		}
		return stmt;
	}


	/**
	 * 
	 * @param dma
	 * @throws SQLException
	 * @throws Exception
	 */
	private static  void getDataBaseInfo(DatabaseMetaData dma) throws SQLException,Exception{
		System.out.println("\n Connected to		" +dma.getURL());
		System.out.println("\n Driver			" +dma.getDriverName());
		System.out.println("\n Version			" + dma.getDriverVersion()+"\n");
	}

	/**
	 * 
	 * @param warn
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private static boolean checkForWarning(SQLWarning warn) throws SQLException,Exception {
		boolean rc = false;
		
		if(warn != null){
			if(DEBUG)
				System.out.println("********* Warning ***********\n");
			
			rc = true;

			while(warn != null){
				if(DEBUG)System.out.println("SQLState:	" +warn.getSQLState());
				if(DEBUG)System.out.println("Message :	" +warn.getMessage());
				if(DEBUG)System.out.println("Vendor :	" +warn.getErrorCode()+"\n");
				warn.getNextWarning();
			}
		}
		return rc;
	}

	
	/**
	 * 
	 * @param connection
	 * @param stmt
	 * @param rs
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void closeConnection(Connection connection,Statement stmt,ResultSet rs) throws SQLException,Exception{
		
		try{

			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
		}catch(Exception e){
			System.out.println("DB-MSG:CC3: inside closeConnection(Connection connection,Statement stmt,ResultSet rs)...");
			e.printStackTrace(System.out);
			throw e;
		}finally{
			closeConnectionDbcp(connection, stmt, rs);
			//dbResult = null; 
			connection = null; stmt = null; rs=null;
		}
	}

	
	/**
	 * 
	 * @param connection
	 * @param stmt
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void closeConnection(Connection connection,Statement stmt) throws SQLException,Exception {
	
		try{
			if(stmt!=null) stmt.close();
		}catch(Exception e) {
			System.out.println("DB-MSG:CC2: inside closeConnection(Connection connection,Statement stmt)...");
			e.printStackTrace(System.out);
			throw e;
		}finally{
			closeConnectionDbcp(connection, stmt, null);
			//dbResult = null; 
			connection = null; stmt = null;
		}
	}

	
	/**
	 * 
	 * @param query
	 */
	private static void printQuery(String query){
		System.out.println(query);
	}



	/**
	 * 
	 * @param connection
	 * @param stmt
	 * @param rs
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void closeConnectionReadOnly(Connection connection,Statement stmt,ResultSet rs) throws SQLException,Exception {
	 
		try{
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
		}catch(Exception e){
			System.out.println("DB-MSG:CC3: inside closeConnection(Connection connection,Statement stmt,ResultSet rs)...");
			e.printStackTrace(System.out);
			throw e;
		}finally{
			closeConnectionDbcp(connection, stmt, rs);
			//dbResult = null; 
			connection = null; stmt = null; rs=null;
		}
	}

	
	/**
	 * 
	 * @param sqlStatements
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DataBaseException
	 * @throws Exception
	 */
	public static void executeBatch(Vector sqlStatements) throws ClassNotFoundException,SQLException,Exception {
		Connection connection = null;
		Statement stmt =null;
		int i=0;
		
		try{
			/* commented by Uday 31 Aug 2012
			connection = ConnectionPool.getConnection();
			*/
  			connection = basicDataSource.getConnection();

			connection.setAutoCommit(false);

			stmt=createStatement(connection);
			
			for(i=0;i<sqlStatements.size();i++){
				stmt.addBatch((String)sqlStatements.elementAt(i));
			}
			stmt.executeBatch();
			connection.commit();

		}catch(SQLException ex){
			connection.rollback();
			ex.printStackTrace(System.out);
			ex.setNextException(new SQLException("Batch Stmt "));
			throw ex;
		}catch(Exception ex){
			connection.rollback();
			System.out.println("DB-MSG:EB: inside executeBatch(Vector sqlStatements)...");
			ex.printStackTrace(System.out);
			throw ex;
		}finally{
			connection.setAutoCommit(true);
			/* commented by Uday 31 Aug 2012
			closeConnection(connection,stmt);
			*/
			closeConnectionDbcp(connection, stmt);
			stmt = null; connection = null;
		}
	}


	
	/**
	 * 
	 * @param sqlStatements
	 * @param duration
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DataBaseException
	 * @throws Exception
	 */
    public static void executeBatch(Vector sqlStatements,int duration) throws ClassNotFoundException,SQLException,Exception {
    	Connection connection = null;
		Statement stmt =null;
		int i=0;
		
		try{
			/* commented by Uday 31 Aug 2012
			connection = ConnectionPoolReadOnly.getConnection(duration);
			*/
  			connection = basicDataSource.getConnection();

			connection.setAutoCommit(false);
			stmt=createStatement(connection);
			
			for(i=0;i<sqlStatements.size();i++){
				stmt.addBatch((String)sqlStatements.elementAt(i));
			}
			
			stmt.executeBatch();
			connection.commit();

		}catch(SQLException ex){

			connection.rollback();
			ex.printStackTrace(System.out);
			ex.setNextException(new SQLException("Batch Stmt "));
			throw ex;
		}catch(Exception ex){
			connection.rollback();
			System.out.println("DB-MSG:EB: inside executeBatch(Vector sqlStatements)...");
			ex.printStackTrace(System.out);
			throw ex;
		}finally{
			connection.setAutoCommit(true);
			/* commented by Uday 31 Aug 2012
			closeConnection(connection,stmt);
			*/
			closeConnectionDbcp(connection, stmt);
			stmt = null; connection = null;
		}
	}

		
    /**
     * 
     * @param obj
     * @param query
     * @throws Exception
     */
    public static void storeObject(Object obj,String query) throws Exception {

    	boolean b=false;
    	/* commented by Uday 31 Aug 2012
		Connection connection = ConnectionPool.getConnection();
		*/
		Connection connection = basicDataSource.getConnection();

		PreparedStatement stmt = connection.prepareStatement(query);
		
		try{
			ByteArrayOutputStream  ostream = new  	ByteArrayOutputStream();
			ZipOutputStream zip=new  ZipOutputStream(ostream);
			ZipEntry zipentry= new ZipEntry("object.ser");
			zip.putNextEntry(zipentry);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(zip);
			objectOutputStream.writeObject(obj);
			zip.closeEntry();

			stmt.setBinaryStream(1,new ByteArrayInputStream(ostream.toByteArray()),ostream.size());
			b=stmt.execute();
			ostream.close();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			System.out.println("ex:"+ex);
        }finally{
        	/* commented by Uday 31 Aug 2012
        	closeConnection(connection,stmt);
        	*/
        	closeConnectionDbcp(connection, stmt);
        	stmt = null; connection = null;
		}
    }


	/**
	 *   
	 * @param query
	 * @return
	 * @throws Exception
	 */
    public static Object retrieveObject(String query) throws Exception {

    	Object obj=new Object();
		/* commented by Uday 31 Aug 2012
    	Connection connection = ConnectionPool.getConnection();
		*/
    	Connection connection = basicDataSource.getConnection();

		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs=stmt.executeQuery();
		rs.next();
		
		try{
			ZipInputStream zipinputstream = new ZipInputStream(rs.getBinaryStream(1));
			ZipEntry zipentry = zipinputstream.getNextEntry();
			ObjectInputStream objectInputStream = new ObjectInputStream(zipinputstream);

			obj= objectInputStream.readObject();
			zipinputstream.closeEntry();
			zipinputstream.close();
		}catch(Exception ex){
			ex.printStackTrace();
        }finally{
			rs.close();
			/* commented by Uday 31 Aug 2012
			closeConnection(connection,stmt);
			*/
			closeConnectionDbcp(connection, stmt);
			stmt = null; connection = null;
		}
		return obj;
    }
	  
	
    /**
     * 
     * @return
     * @throws Exception
     */
	public static Connection getDbcpConnection()throws Exception{
		return basicDataSource.getConnection();
	}

	  

	/**
	 * 
	 * @param connection
	 * @param stmt
	 * @param rs
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void closeConnectionDbcp(Connection connection,Statement stmt,ResultSet rs) throws SQLException,Exception {
		
		try{
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(connection!=null) connection.close();
		}catch(Exception e) {
			System.out.println("DB-MSG:CC3: inside closeConnectionDbcp(Connection connection,Statement stmt,ResultSet rs)...");
			e.printStackTrace(System.out);
			throw e;
		}
	}

	/**
	 * 
	 * @param connection
	 * @param stmt
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void closeConnectionDbcp(Connection connection,Statement stmt) throws SQLException,Exception {
		try{
			if(connection!=null) connection.close();
			if(stmt!=null) stmt.close();
		}catch(Exception e){
			System.out.println("DB-MSG:CC2: inside closeConnectionDbcp(Connection connection,Statement stmt)...");
			e.printStackTrace(System.out);
			throw e;
		}
	}


	private static void createDataSource(){		
		
		basicDataSource = new BasicDataSource();
		
		basicDataSource.setUsername(userName);
		basicDataSource.setPassword(userPassword);
		basicDataSource.setDriverClassName(driverString);
		basicDataSource.setUrl(connectionString);
		basicDataSource.setMaxIdle(10);
		basicDataSource.setMaxActive(10);
		basicDataSource.addConnectionProperty("initialSize", "5");
		
		/*
		basicDataSource.setUsername("OmniEMR");
		basicDataSource.setPassword("0mn!EMR@1DB");
		basicDataSource.setDriverClassName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		basicDataSource.setUrl("jdbc:microsoft:sqlserver://login.omnimd.com:2433;DatabaseName=Omnimdv3");
		*/
		
	}
	
	public static List<Map<String, Object>> list(String query) throws ClassNotFoundException, SQLException, Exception{
		return executeSelect_List("",query,0,0,false,0);
	}
	
	public static List<Map<String, Object>> executeSelect_List(String columns,String querySuffix,int listPageSize,int pageNumber,boolean isCountNeeded,int duration) throws ClassNotFoundException,SQLException,Exception {

		DbResultset dbResult = null;
		Connection connection=null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		
		

		long starttime = new java.util.Date().getTime();

		try{
			int totalRows=-1;
			String query="";
			
			//THIS PART FETCHES NUMBER OF ROWS
			if(!columns.equals("") && !(querySuffix.toLowerCase()).trim().startsWith("from")) 
				throw new Exception("DbBean:Invalid Query Suffix (doesn't start with from)");

			if(isCountNeeded){
	  			/*
	  			 commented by Uday 31 Aug 2012	
				 connection = ConnectionPoolReadOnly.getConnection(duration);				
				*/
				
	  			connection = basicDataSource.getConnection();
				int index=querySuffix.lastIndexOf("order by");
				if (index==-1) index=querySuffix.length();
				String querySuffixCount=querySuffix.substring(0,index);
				query= "Select count(1) TotalRows " + querySuffixCount;
				
				stmt = connection.prepareStatement(query);
				rs = stmt.executeQuery();
				rs.next();
				totalRows=rs.getInt("TotalRows");
				
				/* commented by Uday 31 Aug 2012
				closeConnectionReadOnly(connection,stmt,rs);
				*/
				closeConnectionDbcp(connection, stmt, rs);
				connection = null; stmt = null; rs= null;
  			}

			//THIS PART GETS ACTUAL DATA

	  		/* commented by Uday 31 Aug 2012
			connection= ConnectionPoolReadOnly.getConnection(duration);
			*/
			connection = basicDataSource.getConnection();
			String selectClause = "";
			
			if(listPageSize > 0) 
				selectClause = "Select Top " + (listPageSize * pageNumber) + " ";
			
			query = selectClause + columns + querySuffix;
			stmt = connection.prepareStatement(query);

			rs = stmt.executeQuery();
			
            if(DEBUG)
            	System.out.println("rs ******** class   "+rs.getClass());

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			Hashtable<Integer, String> columnNames = new Hashtable<Integer, String>();
			
			for(int i=1;i<=columnCount;i++){
				columnNames.put(new Integer(i-1),rsmd.getColumnName(i));
			}

			//Vector rowWiseData = new Vector(listPageSize,10);
			List<Map<String, Object>> rowWiseData = null;
			try{
				for(int k=0;k<listPageSize*(pageNumber-1);k++){
					rs.next();
				}
			}catch(Exception e){
				System.out.println(""+e);
			}
			rowWiseData = new ArrayList<Map<String,Object>>();
			while(rs.next()) {
				
				//Vector tempDataVector = new Vector(columnCount);
				
				
				Map<String, Object> map = new HashMap<String, Object>();
				for(int i=1; i<=columnCount; i++){

					String resultData = rs.getString(i);

					if(resultData== null){
						resultData="";
					}
					//tempDataVector.addElement(resultData);
					
					map.put(columnNames.get(i-1), resultData);
				}
				//rowWiseData.addElement(tempDataVector);
				rowWiseData.add(map);
			}


			/*
			if(!rowWiseData.isEmpty()){
				dbResult=new DbResultset(columnNames, rowWiseData,totalRows);
			}else
				dbResult = new DbResultset();
			*/
			java.util.Date currentTime=new java.util.Date();
			long exectime = (new java.util.Date().getTime() - starttime);
		  	if(DEBUG || exectime > 5000 ) System.out.println("DB-MSG:ES3: Currenttime :"+currentTime+" Exectime: "+exectime+" millisecs ARGS - /Cols:"+columns+" /Sufx:"+querySuffix+" /pgSize:"+listPageSize+" /pgNum:"+pageNumber+" /Count:"+isCountNeeded);

			return rowWiseData;
			
			}
		catch(Exception e){
			System.out.println("DB-MSG:ES3: inside executeSelect(String columns,String querySuffix,int listPageSize,int pageNumber,boolean isCountNeeded)..."+columns+querySuffix);
			e.printStackTrace(System.out);
			throw e;
		}finally{
			/* commented by Uday 31 Aug 2012
			closeConnectionReadOnly(connection,stmt,rs);
			*/
			closeConnectionDbcp(connection, stmt, rs);
			//dbResult = null; 
			connection = null; stmt = null; rs=null;
			dbResult = null;
		}
		

	}

	public static void main(String[] args) throws Exception {
		Query query = new Query();
		Connection connection = basicDataSource.getConnection();
		if(connection != null){
			System.out.println("Got the connection");
		}else{
			System.out.println("Failed to get the connection!!");
		}
	}
}

