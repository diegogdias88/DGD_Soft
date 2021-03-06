    package javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafxmvc.model.domain.CidadeEstado;


public class CidEstDao {
	
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<String> listaEstados() {
	String sql = "select nom_estado from estado order by nom_estado";
	List<String> listaEstado = new ArrayList<String>();//Crie uma lista para armazenar os dados do banco
		
            try {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();			
		while(result.next()) {
		listaEstado.add(result.getString("nom_estado"));//Adicione cada linha retornada do banco
                }			
                    result.close();
                    statement.close();
            } catch (SQLException e) {
            }	
        return listaEstado;//Retorne a lista de String com todos os nome do banco de dados
    }

    public List<CidadeEstado> listarTodosEtados(){
        String sql = "select nom_estado from estado order by nom_estado";
	List<CidadeEstado> listaEstado = new ArrayList<>();//Crie uma lista para armazenar os dados do banco
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                CidadeEstado cid = new CidadeEstado();
                cid.setEstado(rs.getString("nom_estado"));
                listaEstado.add(cid);
            }
            
        }catch(SQLException ex){
            Logger.getLogger(CidEstDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        return listaEstado;
    }
    
    public List<String> listaPais() {
        String sql = "select nom_pais from pais order by nom_pais";
	List<String> listaPaises = new ArrayList<String>();//Crie uma lista para armazenar os dados do banco
            try {
                try (PreparedStatement statement = connection.prepareStatement(sql); 
                     ResultSet result = statement.executeQuery()) {
                     while(result.next()) {
                          listaPaises.add(result.getString("nom_pais"));//Adicione cada linha retornada do banco
                    }
                }
            } catch (SQLException e) {
            }	
        return listaPaises;//Retorne a lista de String com todos os nome do banco de dados
    }
   
        
    public List<String> listaCidades() {
        String sql = "select nom_cidade from cidade order by nom_cidade";
	List<String> listaCidades = new ArrayList<String>();//Crie uma lista para armazenar os dados do banco
            try {
                try (PreparedStatement statement = connection.prepareStatement(sql); 
                     ResultSet result = statement.executeQuery()) {
                     while(result.next()) {
                          listaCidades.add(result.getString("nom_cidade"));//Adicione cada linha retornada do banco
                    }
                }
            } catch (SQLException e) {
            }	
        return listaCidades;//Retorne a lista de String com todos os nome do banco de dados
    }    
       
    public List<CidadeEstado> getCidadeByEstado(String nom_cidade) throws Exception {
        String select = "SELECT nom_cidade FROM cidade WHERE estado = ?";
        CidadeEstado cidade = null;
        PreparedStatement stmt = getConnection().prepareStatement(select);
        List<CidadeEstado> cidades = new ArrayList<CidadeEstado>(); 
        stmt.setString(1, nom_cidade);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            cidade = new CidadeEstado();
            cidade.setNom_cidade(rs.getString("nom_cidade"));
            cidades.add(cidade);
        }
        rs.close();
        stmt.close();
        return cidades;
    }        
}
