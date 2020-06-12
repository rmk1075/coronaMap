<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>확진자 정보</title>
</head>
<body>

	<h1>확진자 정보</h1>
	<br> 확진자 전체 수 : ${count}
	<br>
	<br>

	<c:forEach items="${list}" var="patientInfo">
		${patientInfo.idx}<br>
		${patientInfo.age}<br>
		${patientInfo.gender}<br>
		${patientInfo.address}<br>
		${patientInfo.lat}<br>
		${patientInfo.lng}<br>
		<br>
	</c:forEach>
	<br>
	<br>

	<c:forEach items="${pageStartList}" var="pageIndex" varStatus="status">
		<a href="list?start=${pageIndex}">${status.index +1 }</a>&nbsp; &nbsp;
	</c:forEach>

	<br>
	<br>
</body>
</html>