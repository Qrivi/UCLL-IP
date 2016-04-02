<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../parts/header.jsp">
    <jsp:param name="page" value="View comments"/>
</jsp:include>

<section id="passwordform">
    <form method="post" action="">
        <h4>Edit &ldquo;StadLeuven Wi-Fi&rdquo;</h4>
        <i class="fa fa-close"></i>
        <input type="text" placeholder="Password"><span class="error">A password is required</span>
        <div class="buttons">
            <input type="submit" value="Opslaan" class="btnSubmit">
        </div>
    </form>
</section>

<jsp:include page="parts/footer.jsp"/>