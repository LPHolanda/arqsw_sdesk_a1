package br.usjt.arqsw.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.arqsw.dao.ConnectionFactory;
import br.usjt.arqsw.dao.FilaDAO;
import br.usjt.arqsw.entity.Fila;

public class FilaService {
	private FilaDAO dao;
	
	public FilaService() {
		dao = new FilaDAO();
	}
	public ArrayList<Fila> listarFilas() throws IOException{
		return dao.listarFilas();
	}
	public Fila carregar(int id) throws IOException{
		
		Fila fila = new Fila();
		String query = "select id_fila, nm_fila from fila where id_fila = ?";
		
		try(Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pst = conn.prepareStatement(query);){
				
			pst.setInt(1, id);
			try(ResultSet rs = pst.executeQuery();){
				if(rs.next()) {
					fila.setId(rs.getInt("id_fila"));
					fila.setNome(rs.getString("nm_fila"));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
				
		} catch (SQLException e) {
			throw new IOException(e);
		}
		
		return fila;
		
	}
}
