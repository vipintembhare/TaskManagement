<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:bind path="projectList"/>
<spring:bind path="tasks" />
<html>
    <head>
	    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/ajax.js"></script>
    </head>
    <body>
    <div id="valueHolderId" hidden="true"><%=request.getContextPath()%>/show/tasksbyprojectID</div>
      <h3>View Tasks</h3>
        <form:form method="POST" modelAttribute="task">
        <form:errors path="*" cssClass="errorblock" element="div" />
            <form:label path="project">Filter by Project : </form:label>
            <form:select path="project" items="${projectList}" itemValue="projectId" itemLabel="projectName"/>
             <table id="mainTable">
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
                 <tr><td></td></tr>
                 
                <c:forEach items="${tasks}" var="task">
                    <tr>
                        <td><form:label path="taskDesc"><b>Task Description </b> : </form:label></td>
                        <td><c:out value="${task.taskDesc}" /></td>
                    </tr>
                    <tr>
                        <td>Task Start Date :</td>
                        <td><c:out value="${task.startDate}" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="endDate">Task End Date :</form:label></td>
                       <td><c:out value="${task.endDate}" /></td>
                    </tr>
                     <tr>
                      <td colspan="2"> <table border="1" width="100%">
                             <tr>
                                 <th>MID</th>
                                 <th>Employee Name</th>   
                            </tr>
                            <c:forEach items="${task.assignedResources}" var="assignedResources">
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
        </form:form>
    </body>
       
</html>
