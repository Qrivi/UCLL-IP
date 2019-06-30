<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:message code="var.Index" var="varIndex"/>
<s:message code="var.AddNetwork" var="varAddNetwork"/>
<s:message code="var.EditNetwork" var="varEditNetwork"/>
<html lang="<s:message code="var.Lang"/>">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="utf-8">
    <title><s:message code="lbl.Networks"/><c:if test="${param.page != 'index'}"> &raquo; ${param.page}</c:if></title>

    <link rel="stylesheet" media="screen" href="<c:url value="/resources/css/screen.css"/>">

    <c:choose>
        <c:when test="${param.page == varIndex}">
            <script src="<c:url value="/resources/js/app.js"/>"></script>
            <script src="https://unpkg.com/masonry-layout@4.0/dist/masonry.pkgd.min.js"></script>
            <script async defer
                    src="https://maps.googleapis.com/maps/api/js?callback=init&key=MAPS_KEY"></script>
        </c:when>
        <c:when test="${param.page == varAddNetwork || param.page == varEditNetwork}">
            <script src="<c:url value="/resources/js/wifi.js"/>"></script>
        </c:when>
    </c:choose>
</head>
<body>
<header>
    <section id="top">
        <h1><a href="<c:url value="/"/>"><s:message code="lbl.Networks"/></a></h1>
        <c:if test="${param.page == varIndex}">
            <a href="<c:url value="/add"/>"><i class="fa fa-plus-circle"></i> <s:message code="lbl.AddNetwork"/></a>
            <form action="" method="get">
                <label for="txt_cityName"><s:message code="lbl.Near"/></label>
                <input type="text" name="city" value="<c:out value="${param.city}"/>" id="txt_cityName"
                       autocomplete="off" maxlength="20">
                <input type="submit" class="fa" value="&#xf040;">
            </form>
        </c:if>
    </section>
</header>

<main>