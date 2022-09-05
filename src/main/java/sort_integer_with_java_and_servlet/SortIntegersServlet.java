package sort_integer_with_java_and_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sort_integer_with_java.SortIntegers;

public class SortIntegersServlet extends HttpServlet{
	private static final long serialVersionUID = -148528493689621324L;
	public static String paramOfIntsInRequest = "intsToSort";
	
	public void doGet(HttpServletRequest request, 
	  HttpServletResponse response)
	  throws ServletException,IOException{
	  response.setContentType("application/json");
	  response.setCharacterEncoding("UTF-8");
	  PrintWriter pw = response.getWriter();
	  pw.println(computeRequest(request));
	}
	
	/**
	 * reads ints from request, sort them and build json response to be inserted in response
	 * example url : /sortIntegers?intsToSort=8,4,9
	 * @param request containing integers to sort
	 * @return String representing json array of sorted ints received in request
	 */
	private static String computeRequest(HttpServletRequest request) {
		String toReturn = null;
		  //get integers from request
		  String sInts = request.getParameter(paramOfIntsInRequest);
		  if(sInts == null) {
			  toReturn = "{'error': can't find parameter "+paramOfIntsInRequest+"}";
		  } else if(sInts.equals("")) {
			  toReturn = "{'error': parameter "+paramOfIntsInRequest+" empty}";
		  } else {
			  String[] sIntsToSort = sInts.split(",");
			  boolean dataValid = true; 
			  for(String sIntToSort : sIntsToSort) {
				  try{
					  Integer.valueOf(sIntToSort);
				  } catch(NumberFormatException e) {
					  toReturn = "{'error': parameter "+sIntToSort+" not an integer}";
					  dataValid = false;
					  break;
				  }
			  }
			  if(dataValid) {
				  int[] intsToSort = new int[sIntsToSort.length];
				  for(int cpt = 0; cpt < sIntsToSort.length; cpt++) {
					  intsToSort[cpt] = Integer.valueOf(sIntsToSort[cpt]);
				  }
				  SortIntegers.sort(intsToSort);
				  StringBuilder sb = new StringBuilder();
				  sb.append("[");
				  for(int cpt = 0; cpt < intsToSort.length-1;cpt++) {
					  sb.append(intsToSort[cpt]).append(",");
				  }
				  sb.append(intsToSort[intsToSort.length-1]);
				  sb.append("]");
				  toReturn = sb.toString();
			  }
		  }
		  return toReturn;
	}
}
