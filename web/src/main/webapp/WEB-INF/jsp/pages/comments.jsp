<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<s:message code="var.Comments" var="varComments"/>
<s:message code="plh.AddComment" var="plhAddComment"/>
<jsp:include page="../parts/header.jsp">
    <jsp:param name="page" value="${varComments}"/>
</jsp:include>

<section id="comments">
    <h4><s:message code="lbl.CommentsOnArg" arguments="${network.ssid}"/></h4>
    <i class="fa fa-close"></i>
    <c:choose>
        <c:when test="${network.comments.size() > 0}">
            <ul>
                <c:forEach var="item" items="${network.comments}">
                    <li>
                        <c:out value="${item.comment}"/>
                        <span class="timestamp">
                            <c:out value="${item.timestamp}"/>
                        </span>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p><s:message code="lbl.NoCommentsYet"/></p>
        </c:otherwise>
    </c:choose>
    <sf:form method="post" commandName="commentform">
        <sf:input placeholder="${plhAddComment}" maxlength="140" path="message" tabindex="1"/>
        <input type="submit" value="&#xf1d9;" class="fa">
        <span class="error" data-error-field="message"><sf:errors path="message"/></span>
    </sf:form>
</section>

<jsp:include page="../parts/footer.jsp"/>