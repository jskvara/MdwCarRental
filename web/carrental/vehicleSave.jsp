<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/carrental/_header.jsp"%>

<h1>Add vehicle</h1>

<p><a href="/vehicle">Vehicles</a></p>

<form method="post" action="/vehicle/save">
	<table>
		<tr>
			<th><label for="brand">Brand:</label></th>
			<td>
				<input type="text" id="brand" ${f:text("brand")} />
			</td>
		</tr>
		<tr>
			<th><label for="vin">VIN:</label></th>
			<td>
				<input type="text" id="vin" ${f:text("vin")} />
			</td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td>
				<c:if test="${not empty id}"><input type="hidden" ${f:hidden("id")} /></c:if>
				<input type="submit" name="submit" id="submit" value="Save" />
			</td>
		</tr>
	</table>
</form>

<%@include file="/carrental/_footer.jsp"%>