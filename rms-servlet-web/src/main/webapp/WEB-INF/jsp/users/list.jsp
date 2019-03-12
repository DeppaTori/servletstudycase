<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "rms" uri = "/WEB-INF/tags/link.tld"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<t:simple_layout>


	<jsp:attribute name="body_area">

<table
			class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
              <thead>
                <tr>
                  <th class="mdl-data-table__cell--non-numeric">User Name</th>
                  <th>Password</th>
                  <th colpasn="2">&nbsp;</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${users}" var="user">
                  <tr>
                    <td class="mdl-data-table__cell--non-numeric"><c:out
								value="${user.userName}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><a
							href="<c:url value = "/users/form?id="/><c:out value = "${user.id}" />">Edit</a></td>
                    <td><a
							href="<c:url value = "/users/delete?id="/><c:out value = "${user.id}" />">Delete</a></td>
                  </tr>
              </c:forEach>
              </tbody>
            </table>
  
</jsp:attribute>

</t:simple_layout>
