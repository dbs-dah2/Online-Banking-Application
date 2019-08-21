<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Employee Registration</title>
    <style>
    .error{
        color:red;
        font-size: 15px;
        padding:10px;
    }
    </style>
</head>
<body>

<form:form action="/users/register" method="POST" modelAttribute="employee">
    <div class="form-group">
        <label>Name:</label><form:input path="name" size="30" cssClass="form-control" placeholder="Enter name" />
        <small><form:errors path="name" cssClass="error" /></small>
    </div>
    <div class="form-group">
        <label>Age:</label><form:input path="age" cssClass="form-control" placeholder="Enter age" />
        <small><form:errors path="age" cssClass="error" /></small>
    </div>
    <div class="form-group">
        <label>Date of Birth:</label><form:input path="dateOfBirth" cssClass="form-control" placeholder="Enter date of birth in dd-MM-yy format" />
        <small><form:errors path="dateOfBirth" cssClass="error" /></small>
    </div>
    <div class="form-group">
        <label>Salary:</label><form:input path="salary" cssClass="form-control" placeholder="Enter Salary" />
        <small><form:errors path="salary" cssClass="error" /></small>
    </div>
    <div class="form-group">
        <button type="submit" >Submit</button>
    </div>
</form:form>

</body>
</html>