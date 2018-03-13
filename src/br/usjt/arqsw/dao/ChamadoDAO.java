package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

public class ChamadoDAO {

	public int criarChamado(Chamado chamado) throws IOException{
		Date dataAbertura = new Date(chamado.getDataAbertura().getTime());
		Date dataFechamento = new Date(chamado.getDataFechamento().getTime());
		// TODO Auto-generated method stub
		return 0;
	}
	
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException{
		
		String query = "select c.ID_CHAMADO, c.DESCRICAO,  c.DT_ABERTURA, c.DT_FECHAMENTO, " + 
				"c.ID_FILA, f.NM_FILA from fila f left join chamado c on " + 
				"c.id_fila = f.id_fila where c.id_fila = ?";
		
		ArrayList<Chamado> lista = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pst = conn.prepareStatement(query);){
			
			pst.setInt(1, fila.getId());
			try (ResultSet rs = pst.executeQuery()){
				while(rs.next()) {
					Chamado chamado = new Chamado();
					chamado.setNumero(rs.getInt("id_chamado"));
					chamado.setDescricao(rs.getString("descricao"));
					chamado.setStatus(rs.getString("status"));
					chamado.setDataAbertura(rs.getDate("dt_abertura"));
					chamado.setDataAbertura(rs.getDate("dt_fechamento"));
					chamado.setFila(fila);
					lista.add(chamado);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}
}
