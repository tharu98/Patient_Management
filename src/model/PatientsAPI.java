package model; 

import model.Patient;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HospitalAPI
 */
@WebServlet("/PatientsAPI")
public class PatientsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Patient patientObj = new Patient();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientsAPI() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //list the patients by get request
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//post request-insert
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String output = patientObj.insertPatientDetails(request.getParameter("first_name"),      
												request.getParameter("last_name"),     
												request.getParameter("address"),       
												request.getParameter("email")); 
		 
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	//put request -update
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 
		String output = patientObj.updatePatientDetails(paras.get("hidPatientIDSave").toString(),     
				 								paras.get("first_name").toString().replace('+',' '),     
				 								paras.get("last_name").toString().replace("%2C",","),        
				 								paras.get("address").toString(),        
				 								paras.get("email").toString().replace("%40","@" )); 
		 
		 response.getWriter().write(output);
	}
	

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	//
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		Map paras = getParasMap(request); 
		 
		String output = patientObj.deletePatientDetails(paras.get("patientID").toString()); 
		 
		response.getWriter().write(output);
	}
	
	
	private static Map getParasMap(HttpServletRequest request) 
	{  
		Map<String, String> map = new HashMap<String, String>();  
		try  
		{   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?          
					scanner.useDelimiter("\\A").next() : "";   
					scanner.close(); 
	 
					String[] params = queryString.split("&");   
					for (String param : params)   
					{ 
		  
						String[] p = param.split("=");    
						map.put(p[0], p[1]);   
					}  
		}  
		catch (Exception e)  
		{  
			
		}  
		return map; 
		
	}
	
	
	

}
