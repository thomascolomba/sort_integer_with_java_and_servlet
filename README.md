# sort\_integer\_with_java with a servlet

Just servlet ready to call sort\_integer\_with_java and return the results


-- pom.xml <br/>
dependency : sort\_integer\_with_java<br/>
dependency : javax.servlet-api (4.0.1)<br/>

<br/>
-- SortIntegersServlet.java (extends HttpServlet)<br/>
doGet(HttpServletRequest request, HttpServletResponse response)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;response.setContentType("application/json");<br/>
&nbsp;&nbsp;&nbsp;&nbsp;response.setCharacterEncoding("UTF-8");<br/>
&nbsp;&nbsp;&nbsp;&nbsp;PrintWriter pw = response.getWriter();<br/>
&nbsp;&nbsp;&nbsp;&nbsp;pw.println(computeRequest(request));<br/>
	