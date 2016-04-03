<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../parts/header.jsp">
    <jsp:param name="page" value="Edit password"/>
</jsp:include>

<section id="passwordform">
    <sf:form method="post" commandName="passwordform">
        <h4>Edit &ldquo;${passwordform.ssid}&rdquo;</h4>
        <i class="fa fa-close"></i>
        <sf:input placeholder="New password" path="password" tabindex="1"/>
        <span class="error"><sf:errors path="password"/></span>
        <div class="buttons">
            <input type="submit" value="Opslaan" class="btnSubmit">
        </div>
    </sf:form>
</section>

<jsp:include page="../parts/footer.jsp"/>