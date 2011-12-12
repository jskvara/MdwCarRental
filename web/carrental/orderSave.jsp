<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/carrental/_header.jsp"%>

<h1>Add order</h1>

<p><a href="/order">Orders</a></p>

<form method="post" action="/order/save">
	<table>
		<tr>
			<th><label for="date">Date:</label></th>
			<td>
				<input type="text" id="date" ${f:text("date")} /> (dd.mm.yyyy)
			</td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td>
				<c:if test="${not empty id}"><input type="hidden" ${f:hidden("id")} /></c:if>
				<input type="hidden" ${f:hidden("vehicleId")} />
				<input type="submit" name="submit" id="submit" value="Save" />
			</td>
		</tr>
	</table>
</form>

<%@include file="/carrental/_footer.jsp"%>