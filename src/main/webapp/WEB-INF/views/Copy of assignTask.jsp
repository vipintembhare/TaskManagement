<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
	    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
	    <script src="/js/ajax.js"></script>
    </head>
    <body>
        <h3>Assign Tasks</h3>
        <form:form method="POST" action="/task/add" modelAttribute="task">
             <table>
                <tr>
                    <td><form:label path="project">Project</form:label></td>
                    <td>
                    <select id="projectId" name="projectId">
                        <option value="">Select Project</option> 
                        <c:forEach items="${projectList}" var="projects">
                            <option   value="${project.projectId}"  >${project.projectName}</option>
                        </c:forEach>
                    </select>
                </td>
                </tr>
                <tr>
                    <td><form:label path="taskDesc">Description</form:label></td>
                    <td><form:input path="taskDesc"/></td>
                </tr>
                <tr>
                    <td><form:label path="startDate">Start Date of Task [dd-mm-yyyy]</form:label></td>
                    <td><form:input type="date" path="startDate"/></td>
                </tr>
                <tr>
                    <td><form:label path="endDate">Due Date of Task [dd-mm-yyyy]</form:label></td>
                    <td><form:input type="date" path="endDate"/></td>
                </tr>
                 <tr>
                    <td><form:label path="assignedResource">Who should do this?</form:label></td>
                    <td>
                    <select  id="employeeId">
                    </select>
                </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Add Task"/></td>
                    <td><input type="submit" value="Cancel"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>