import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import commonj.sdo.DataObject;
import com.ibm.websphere.sca.ServiceManager;
import com.main.DataSourceConector;

public class ObtenerDocumentosImpl {
	/**
	 * Default constructor.
	 */
	public ObtenerDocumentosImpl() {
		super();
	}

	/**
	 * Return a reference to the component service instance for this implementation
	 * class.  This method should be used when passing this service to a partner reference
	 * or if you want to invoke this component service asynchronously.    
	 *
	 * @generated (com.ibm.wbit.java)
	 */
	@SuppressWarnings("unused")
	private Object getMyService() {
		return (Object) ServiceManager.INSTANCE.locateService("self");
	}

	/**
	 * Method generated to support implementation of operation "BuscarDatos" defined for WSDL port type 
	 * named "DatosDocumentos".
	 * 
	 * The presence of commonj.sdo.DataObject as the return type and/or as a parameter 
	 * type conveys that it is a complex type. Please refer to the WSDL Definition for more information 
	 * on the type of input, output and fault(s).
	 */
	public DataObject BuscarDatos(DataObject inputBuscarDatos) {

		com.ibm.websphere.bo.BOFactory boFactory = (com.ibm.websphere.bo.BOFactory) ServiceManager.INSTANCE.locateService("com/ibm/websphere/bo/BOFactory");

		DataSourceConector conector = new DataSourceConector();
		Connection conn = conector.crearConexion_procesoCILIMASIVO();
		PreparedStatement ps = null;

		int i = 0;
		int largoLista = inputBuscarDatos.getList("idFilenet").size();
		DataObject salida = boFactory.create("http://OM_DesasociarDocumentos","out_BuscarDocumentos");;
		List listaAuxiliar = new ArrayList();

		while (i < largoLista) {

			String query = "SELECT ID_FILENET, NRO_SOLICITUD, ID_DOCSYS, ID_HOST "
					+ " FROM OM_DOCUMENTO "
					+ " WHERE ID_FILENET = '"
					+ inputBuscarDatos.getList("idFilenet").get(i) + "'";
			
			DataObject documento = boFactory.create("http://OM_DesasociarDocumentos","BuscarDocumentos");
			
			try {
				ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) 
				{
				
					documento.setString("idFilenet", rs.getString("ID_FILENET"));
					documento.setString("idDocsys", rs.getString("ID_DOCSYS"));
					documento.setString("idOperacion", rs.getString("NRO_SOLICITUD"));
					documento.setString("idHost", rs.getString("ID_HOST"));

				}
				
				} 
			catch (Exception e1) {
										
				if (ps != null) {
					
					try {
						ps.close();
					    } 
					catch (SQLException e) {}
								}
				
				if (conn != null) {
				
					try {
					conn.close();
					} 
				
					catch (SQLException e) {
					}
								   }
				
			
				}
		listaAuxiliar.add(documento);
		i++;
		}
		
		if (ps != null) {
			
			try {
				ps.close();
			    } 
			catch (SQLException e) {}
						}
		
		if (conn != null) {
		
			try {
			conn.close();
			} 
		
			catch (SQLException e) {
			}
						   }
		
		salida.setList("BuscarDocumentos", listaAuxiliar);
		return salida;

	}

}