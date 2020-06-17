<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Movie Ratings</title>
</head>
<body>
    <h3 style="color: red;">Get Movie Ratings</h3>

    <spring:eval expression="@environment.getProperty('authorize.url')" var="authorizeUrl" />
    <spring:eval expression="@environment.getProperty('redirect.url')" var="redirectUrl" />
    <div id="authFirst">
        <form:form action="${authorizeUrl}"
            method="get">
            <p>
                <label>Enter User Details</label>
                 <input type="text" name="response_type" value="code" />
                 <input type="text" name="client_id" value="pavan" />
                 <input type="text" name="redirect_uri" value="${redirectUrl}" />
                 <input type="text" name="scope" value="read" />
                 <input type="SUBMIT" value="Get Movie Ratings" />
        </form:form>
    </div>
</body>
</html>