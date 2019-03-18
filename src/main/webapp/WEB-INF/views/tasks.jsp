<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:bind path="projectList"/>
<spring:bind path="tasks" />
<html>
    <head>
	    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/viewTask.js"></script>
	
    </head>
    <body>
      <h1>View Tasks</h1>
        <form:form method="GET" modelAttribute="task" enctype='application/json'>
        <form:errors path="*" cssClass="errorblock" element="div" />
            <form:label path="project">Filter by Project : </form:label>
            <form:select path="project" items="${projectList}" itemValue="projectId" itemLabel="projectName"/>
             <table id="mainTable" class="mainTable">
                <tr>
                    <td></td>
                </tr>
                 <tr> <td></td></tr>
                 <tr><td></td></tr>
                 <tr><td></td></tr>
                   <tr> <td></td></tr>
                 <tr><td></td></tr>
                 <tr><td></td></tr>
                   <tr> <td></td></tr>
                 <tr><td></td></tr>
                 <tr><td colspan="2"><hr></td></tr>
                 
                <c:forEach items="${tasks}" var="taskk">
                <tr>
                        <td><form:label path="project"><b>Project </b> : </form:label></td>
                        <td><b><c:out value="${taskk.project.projectName}" /></b></td>
                    </tr>
                    <tr>
                        <td><form:label path="taskDesc">Task Description : </form:label></td>
                        <td><c:out value="${taskk.taskDesc}" /></td>
                    </tr>
                    <tr>
                        <td>Task Start Date :</td>
                        <fmt:formatDate pattern = "dd-MM-yyyy" value = "${taskk.startDate}" var="startsDate" />
                        <td><c:out value="${startsDate}" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="endDate">Task End Date :</form:label></td>
                        <fmt:formatDate pattern = "dd-MM-yyyy" value = "${taskk.endDate}" var="endsDate" />
                       <td><c:out value="${endsDate}" /></td>
                    </tr>
                     <tr>
                      <td colspan="2"> <table border="solid black" width="100%" id="empTable">
                             <tr>
                                 <th>MID</th>
                                 <th>Employee Name</th>   
                            </tr>
                            <c:forEach items="${taskk.assignedResources}" var="assignedResources">
                                <tr>
                                    <td><c:out value="${assignedResources.empId}" /></td>
                                    <td><c:out value="${assignedResources.empName}" /></td>
                                </tr>
                            </c:forEach>
                         </table>
                       </td>  
                    </tr>
                     <tr> <td></td></tr>
                     <tr><td></td></tr>
                     <tr><td></td></tr>
                     <tr> <td></td></tr>
                     <tr><td></td></tr>
                     <tr><td colspan="2"><hr></td></tr>
                       
                </c:forEach>
                 </div>
            </table>
            <div id="tableDiv"/>
        </form:form>
    </body>
       
</html>
