<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<s:message code="var.EditNetwork" var="varEditNetwork"/>
<s:message code="plh.Ssid" var="plhSsid"/>
<s:message code="plh.Password" var="plhPassword"/>
<s:message code="plh.Name" var="plhName"/>
<s:message code="plh.Address" var="plhAddress"/>
<s:message code="plh.CrossStreet" var="plhCrossStreet"/>
<s:message code="plh.Zip" var="plhZip"/>
<s:message code="plh.City" var="plhCity"/>
<s:message code="plh.Country" var="plhCountry"/>
<jsp:include page="../parts/header.jsp">
    <jsp:param name="page" value="${varEditNetwork}"/>
</jsp:include>

<section id="networkform">
    <h4><s:message code="lbl.EditArg" arguments="${networkform.networkSsid}"/></h4>
    <i class="fa fa-close"></i>
    <sf:form method="post" commandName="networkform">
        <h5><s:message code="lbl.NetworkDetails"/></h5>
        <div class="left">
            <sf:input type="text" placeholder="${plhSsid}" path="networkSsid" tabindex="1"/>
            <span class="error"><sf:errors path="networkSsid"/></span>
            <sf:checkbox id="box_hasPassword" value="true" path="networkProtected" tabindex="2"/>
            <label for="box_hasPassword"><s:message code="lbl.PasswordProtected"/></label>
        </div>
        <div class="right invisible" id="passwordfield">
            <sf:input type="text" placeholder="${plhPassword}" path="networkPassword" tabindex="3"/>
            <span class="error"><sf:errors path="networkPassword"/></span>
        </div>
        <h5><s:message code="lbl.NetworkLocation"/></h5>
        <div class="left">
            <sf:input type="text" placeholder="${plhName}" path="locationName" tabindex="4"/>
            <span class="error"><sf:errors path="locationName"/></span>
            <sf:input type="text" placeholder="${plhAddress}" path="locationAddress" tabindex="5"/>
            <span class="error"><sf:errors path="locationAddress"/></span>
            <sf:input type="number" min="1" placeholder="${plhZip}" path="locationZip" tabindex="7"/>
            <span class="error"><sf:errors path="locationZip"/></span>
            <sf:input type="text" placeholder="${plhCountry}" path="locationCountry" tabindex="9"/>
            <span class="error"><sf:errors path="locationCountry"/></span>
        </div>
        <div class="location right">
            <sf:input type="text" placeholder="${plhCrossStreet}" path="locationCrossStreet" tabindex="6"/>
            <span class="error"><sf:errors path="locationCrossStreet"/></span>
            <sf:input type="text" placeholder="${plhCity}" path="locationCity" tabindex="8"/>
            <span class="error"><sf:errors path="locationCity"/></span>
        </div>
        <div class="buttons">
            <a class="btnRemove" href="<c:url value='/remove/${networkform.networkId}'/>"><s:message
                    code="btn.Remove"/></a>
            <input type="submit" value="<s:message code="btn.Save"/>" class="btnSubmit">
        </div>
    </sf:form>
</section>

<jsp:include page="../parts/footer.jsp"/>