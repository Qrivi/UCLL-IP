<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../parts/header.jsp">
    <jsp:param name="page" value="Add network"/>
</jsp:include>

<section id="networkform">
    <h4>Add a new network</h4>
    <i class="fa fa-close"></i>
    <sf:form action="/add" method="post" commandName="networkform">
        <h5>Network details</h5>
        <div class="left">
            <sf:input type="text" placeholder="SSID" path="networkSsid" tabindex="1"/>
            <span class="error"><sf:errors path="networkSsid"/></span>
            <sf:checkbox id="box_hasPassword" value="true" path="networkProtected" tabindex="2"/>
            <label for="box_hasPassword">Password protected</label>
        </div>
        <div class="right invisible" id="passwordfield">
            <sf:input type="text" placeholder="Password" path="networkPassword" tabindex="3"/>
            <span class="error"><sf:errors path="networkPassword"/></span>
        </div>
        <h5>Network location</h5>
        <div class="left">
            <sf:input type="text" placeholder="Name" path="locationName" tabindex="4"/>
            <span class="error"><sf:errors path="locationName"/></span>
            <sf:input type="text" placeholder="Address" path="locationAddress" tabindex="5"/>
            <span class="error"><sf:errors path="locationAddress"/></span>
            <sf:input type="number" min="1" placeholder="Postal code" path="locationZip" tabindex="7"/>
            <span class="error"><sf:errors path="locationZip"/></span>
            <sf:input type="text" placeholder="Country" path="locationCountry" tabindex="9"/>
            <span class="error"><sf:errors path="locationCountry"/></span>
        </div>
        <div class="location right">
            <sf:input type="text" placeholder="Cross street" path="locationCrossStreet" tabindex="6"/>
            <span class="error"><sf:errors path="locationCrossStreet"/></span>
            <sf:input type="text" placeholder="City" path="locationCity" tabindex="8"/>
            <span class="error"><sf:errors path="locationCity"/></span>
        </div>
        <div class="buttons">
            <input type="submit" value="Opslaan" class="btnSubmit">
        </div>
    </sf:form>
</section>

<jsp:include page="../parts/footer.jsp"/>