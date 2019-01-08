<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
	    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/ajax.js"></script>
	    
    </head>
    <body>
    <div id="valueHolderId" hidden="true"><%=request.getContextPath()%>/assignTasks/getEmployees</div>
      <h3>Assign Tasks</h3>
        <form:form method="POST" modelAttribute="task" enctype='application/json'>
        <form:errors path="*" cssClass="errorblock" element="div" />
             <table>
                <tr>
                    <td><form:label path="project">Project</form:label></td>
                    <td><form:select path="project" items="${projectList}" itemValue="projectId" itemLabel="projectName"/>
                    <td><form:errors path="project" cssClass="error" /></td>
                </td>
                </tr>
                <tr>
                    <td><form:label path="taskDesc">Description</form:label></td>
                    <td><form:input path="taskDesc"/></td>
                    <td><form:errors path="taskDesc" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><form:label path="startDate">Start Date of Task [dd-mm-yyyy]</form:label></td>
                    <td><form:input type="date" path="startDate" class="date"/></td>
                    <td><form:errors path="startDate" cssClass="error" /></td>
                </tr>
                <tr>
                    <td><form:label path="endDate">Due Date of Task [dd-mm-yyyy]</form:label></td>
                    <td><form:input type="date" path="endDate" class="date"/></td>
                    <td><form:errors path="endDate" cssClass="error" /></td>
                </tr>
                 <tr>
                    <td><form:label path="assignedResources">Who should do this?</form:label></td>
                    <td>
                    <form:select path="assignedResources" id="assignedResources" size="3" multiple="true"/>
                    <td><form:errors path="assignedResources" cssClass="error" /></td>
                </td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Add Task"/></td>
                    <td><input type="submit" value="Cancel"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>