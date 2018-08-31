
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import commonj.sdo.DataObject;
import com.ibm.websphere.sca.ServiceManager;
import com.main.DataSourceConector;

public class UpdateDocumentosImpl {
	/**
	 * Default constructor.
	 */
	public UpdateDocumentosImpl() {
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
	 * Method generated to support implementation of operation "UpdateDocs" defined for WSDL port type 
	 * named "ActualizarDocumentos".
	 * 
	 * The presence of commonj.sdo.DataObject as the return type and/or as a parameter 
	 * type conveys that it is a complex type. Please refer to the WSDL Definition for more information 
	 * on the type of input, output and fault(s).
	 * @return 
	 */
	
	public DataObject UpdateDocus(DataObject inputUpdateDocs) {

		com.ibm.websphere.bo.BOFactory boFactory = (com.ibm.websphere.bo.BOFactory) ServiceManager.INSTANCE.locateService("com/ibm/websphere/bo/BOFactory");

		DataSourceConector conector = new DataSourceConector();
		Connection conn = conector.crearConexion_procesoCILIMASIVO();
		PreparedStatement ps = null;

		DataObject status = boFactory.create("http://OM_DesasociarDocumentos","out_UpdateDocs");
		
		boolean resultadoMal = false;
		int i = 0;
		int largoLista = inputUpdateDocs.getList("idFilenet").size();
				
		while(i < largoLista)
			
		{	
		
		String query = "UPDATE OM_DOCUMENTO "
				+ " SET RESULTADO = '3' "
				+ " ,ID_FILENET = NULL "
				+ " ,MQ_PROCESO = NULL "
				+ " ,MQ_SUBPROCESO = NULL "
				+ " ,MQ_USUARIODIGITALIZADOR = NULL "
				+ " ,LOTE = NULL "
				+ " ,FECHA_TIPO_DOCUMENTAL = NULL "
				+ " ,ID_DOCSYS = NULL "
				+ " ,FECHA_DIGITALIZACION = NULL "
				+ " ,FECHA_EMISION = NULL "
				+ " WHERE ID_FILENET = '"
				+ inputUpdateDocs.getList("idFilenet").get(i) + "'";

		try {
			ps = conn.prepareStatement(query);
			int response = ps.executeUpdate();
			
			if (response < 0)
			{
				//status.setBoolean("estado", false);
				resultadoMal = true;
			}

			} 

		catch (Exception e1) {			
			} 

		i++;
		}

		if (ps != null) {
			try {
				ps.close();
			} 
			catch (SQLException e) {
			}
			}
		
		if (conn != null) {
			try {
				conn.close();
			} 
			catch (SQLException e) {
			}
			}
		
		if(resultadoMal == true){
			
			status.setBoolean("estado", false);
			
		}
		
		return status;
				
	}

}