<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<div id="errors" class="errors">
<html:errors />
</div>
<div id="success" class="success">
<html:messages id="message" message="true" property="message">
<bean:write name="message"/><br>
</html:messages>
</div>