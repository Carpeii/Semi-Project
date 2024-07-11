<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>

<%
	request.setCharacterEncoding( "utf-8" );

	String strYear = request.getParameter( "year" );
	String strMonth = request.getParameter( "month" );	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- calendar_ok.jsp -->
<%
	int year = Integer.parseInt( strYear );
	int month = Integer.parseInt( strMonth );;
	
	int START_DAY_OF_WEEK = 0;
	int END_DAY_OF_WEEK = 0;
	int END_DAY = 0;
	
	Calendar sDay = Calendar.getInstance();
	Calendar eDay = Calendar.getInstance();
	
	sDay.set( year, month-1, 1 );
	eDay.set( year, month, 1-1 );
	
	START_DAY_OF_WEEK = sDay.get( Calendar.DAY_OF_WEEK );
	END_DAY_OF_WEEK = eDay.get( Calendar.DAY_OF_WEEK );
	END_DAY = eDay.get( Calendar.DATE );
     
	out.println( "<table width='800' border='1'>" );
	out.println( "<tr>" );
	out.println( "<td colspan='7'>" + year + "년" + month + "월</td>" );
	out.println( "</tr>" );
	out.println( "<tr>" );
	out.println( "<td>SU</td><td>MO</td><td>TU</td><td>WE</td><td>TH</td><td>FR</td><td>SA</td>" );
	out.println( "</tr>" );
     
	out.println( "<tr>" );
	for( int i=1 ; i<START_DAY_OF_WEEK ; i++ ) {
		out.println( "<td></td>" );
	}
     
	for( int i=1, n=START_DAY_OF_WEEK ; i<=END_DAY ; i++, n++ ) {
		if( n % 7 == 1 ) out.println( "<tr>" );
          
		out.println( "<td>" + i + "</td>" );
          
		if( n % 7 == 0 ) out.println( "</tr>" );
	}
     
	for( int i=END_DAY_OF_WEEK ; i<=6 ; i++ ) {
		out.println( "<td></td>" );
	}
	
	out.println( "</tr>" );
	out.println( "</table>" );
%>
</body>
</html>