<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../parts/header.jsp">
    <jsp:param name="page" value="Comments"/>
</jsp:include>

<section id="comments">
    <h4>Comments on &ldquo;StadLeuven Wi-Fi&rdquo;</h4>
    <i class="fa fa-close"></i>
    <ul>
        <li>Deze wifi lijkt <br>niet te werken</li>
        <li>Ik eet graag frietjes</li>
        <li>lol frietjes ok wifi bestaat idd niet meer :c</li>
    </ul>
    <form method="post" action="">
        <input type="text" placeholder="Add your comment here">
        <input type="submit" value="&#xf1d9;" class="fa">
        <span class="error">You must type a comment first</span>
    </form>
</section>

<jsp:include page="../parts/footer.jsp"/>