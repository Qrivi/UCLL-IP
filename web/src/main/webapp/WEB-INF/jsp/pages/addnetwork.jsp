<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../parts/header.jsp">
    <jsp:param name="page" value="Add WiFi"/>
</jsp:include>

<main>
    <section id="field">
        <sf:form action="/add" method="post" commandName="networkform">
            <h4>WiFi details</h4>

            <p class="description">
                Enter the WiFi details as precisely as possible. You can always edit this later, but goed begonnen is
                half gewonnen.
            </p>

            <h5>Location</h5>
            <label for="txt_locName">Venue name</label><span class="form-error"><sf:errors path="locationName"/></span>
            <sf:input type="text" name="txt_locName" id="txt_locName" placeholder="Melvin's Mighty Meatballs"
                      path="locationName"/>

            <div class="left">
                <label for="txt_locStreet">Address</label><span class="form-error"><sf:errors path="locationAddress"/></span>
                <sf:input type="text" name="txt_locStreet" id="txt_locStreet" placeholder="Greasy Alley 69"
                          path="locationAddress"/>
                <label for="txt_locCode">Postal Code</label><span class="form-error"><sf:errors path="locationZip"/></span>
                <sf:input type="number" name="txt_locCode" id="txt_locCode" placeholder="42069"
                          path="locationZip"/>
                <label for="txt_locCountry">Country</label><span class="form-error"><sf:errors path="locationCountry"/></span>
                <sf:input type="text" name="txt_locCountry" id="txt_locCountry" placeholder="US of Amurucu"
                          path="locationCountry"/>
            </div>
            <div class="right">
                <label for="txt_locCross">Cross Street</label><span class="form-error"><sf:errors path="locationCrossStreet"/></span>
                <sf:input type="text" name="txt_locCross" id="txt_locCross" placeholder="at Fiesty Foodcorner"
                          path="locationCrossStreet"/>
                <label for="txt_locCity">City</label><span class="form-error"><sf:errors path="locationCity"/></span>
                <sf:input type="text" name="txt_locCity" id="txt_locCity" placeholder="Blaze Town"
                          path="locationCity"/>
            </div>

            <h5>Access Point</h5>

            <div class="left">
                <label for="txt_apSsid">SSID</label><span class="form-error"><sf:errors path="networkSsid"/></span>
                <sf:input type="text" name="txt_apSsid" id="txt_apSsid" placeholder="Mighty WiFi"
                          path="networkSsid"/>
            </div>
            <div class="right">
                <label for="txt_apPasscode">Password</label><span class="form-error"><sf:errors path="networkPassword"/></span>
                <sf:input type="text" name="txt_apPasscode" id="txt_apPasscode" placeholder="SuperSecret1234"
                          path="networkPassword"/>
            </div>
            <sf:checkbox name="box_apProtected" id="box_apProtected"
                   path="networkHasPassword" value="true"/>
            <label for="box_apProtected">Requires password</label>

            <div class="confirm">
                <input type="button" class="cancel" value="Cancel">
                <input type="submit" value="Save">
            </div>
        </sf:form>
    </section>
</main>
<jsp:include page="../parts/footer.jsp"/>