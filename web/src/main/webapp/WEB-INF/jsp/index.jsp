<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<s:message code="var.Index" var="varIndex"/>
<jsp:include page="parts/header.jsp">
    <jsp:param name="page" value="${varIndex}"/>
</jsp:include>

<section id="map">
    <s:message code="lbl.LoadingMap"/>
</section>

<section id="cards" data-masonry='{ "itemSelector": "article", "isOriginLeft": false }'>
    <c:forEach var="network" items="${networks}">
        <article data-lat="${network.location.lat}" data-lon="${network.location.lon}">
            <header>
                <h2><c:out value="${network.ssid}"/></h2>
                <p>
                    <c:choose>
                        <c:when test="${network.type == 'OPEN'}">
                            <i class="fa fa-unlock-alt"></i>
                        </c:when>
                        <c:when test="${network.type == 'PROTECTED'}">
                            <i class="fa fa-lock"></i>
                        </c:when>
                    </c:choose>
                </p>
            </header>
            <section>
                <h3><s:message code="lbl.Address"/></h3>
                <a class="edit" href="<c:url value="/edit/${network.id}"/>">&#xf044;</a>

                <p class="address">
                    <c:if test="${not empty network.location.name.trim()}">
                        <em><c:out value="${network.location.name}"/></em><br>
                    </c:if>
                    <c:out value="${network.location.address}"/>
                    <c:if test="${not empty network.location.crossStreet.trim()}">
                        (<c:out value="${network.location.crossStreet}"/>)
                    </c:if>
                    <br>
                    <c:out value="${network.location.zip} ${network.location.city}"/>
                </p>
            </section>
            <c:if test="${network.type == 'PROTECTED'}">
                <section class="passwords">
                    <h3><s:message code="lbl.Password"/></h3>
                    <a class="edit" href="<c:url value="/edit/${network.id}/password"/>">&#xf044;</a>
                    <ul>
                        <c:forEach var="password" items="${network.passwords}">
                            <li><span><c:out value="${password.password}"/></span>
                                <form action="<c:url value="/vote/${network.id}/password/${password.id}"/>"
                                      method="post">
                                    <input type="hidden" name="city" value="<c:out value="${network.location.city}"/>">
                                    <input type="submit" name="upvote" value="&#xf164;">
                                    <span><c:if test="${password.score > 0}">+</c:if>${password.score}</span>
                                    <input type="submit" name="downvote" value="&#xf165;">
                                </form>
                            </li>
                        </c:forEach>
                    </ul>
                </section>
            </c:if>
            <footer>
                <a title="<s:message code="lbl.LeaveComment"/>" href="<c:url value="/comments/${network.id}"/>">
                    <c:choose>
                        <c:when test="${network.comments.size() == 1}">
                            <s:message code="lbl.CommentOnThisNetwork"/>
                        </c:when>
                        <c:otherwise>
                            <s:message code="lbl.CommentsOnThisNetwork" arguments="${network.comments.size()}"/>
                        </c:otherwise>
                    </c:choose>
                    <i class="fa fa-pencil"></i>
                </a>
            </footer>
        </article>
    </c:forEach>
</section>

<jsp:include page="parts/footer.jsp"/>