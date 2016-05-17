<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<s:message code="var.EditPassword" var="varEditPassword"/>
<s:message code="plh.NewPassword" var="plhNewPassword"/>
<jsp:include page="../parts/header.jsp">
    <jsp:param name="page" value="${varEditPassword}"/>
</jsp:include>

<section id="passwordform">
    <sf:form method="post" commandName="passwordform">
        <h4><s:message code="lbl.EditArg" arguments="${passwordform.ssid}"/></h4>
        <i class="fa fa-close"></i>
        <sf:input placeholder="${plhNewPassword}" path="password" tabindex="1"/>
        <span class="error" data-error-field="password"><sf:errors path="password"/></span>
        <div class="buttons">
            <sf:hidden path="ssid"/>
            <input type="submit" value="<s:message code="btn.Save"/>" class="btnSubmit">
        </div>
    </sf:form>
</section>

<jsp:include page="../parts/footer.jsp"/>