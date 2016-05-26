<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</main>

<footer>
    <a href="http://193.191.187.14:10226/web" style="float: left;">Dynamic</a>
    <a href="<c:url value="?lang=en"/>">English</a>
    <a href="<c:url value="?lang=nl"/>">Nederlands</a>
</footer>

<noscript>
    <s:message code="err.javascript"/>
</noscript>
</body>
</html>