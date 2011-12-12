<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/carrental/_header.jsp"%>

<h1>Orders</h1>

<p><a href="/order/save?vehicleId=${vehicle.id}">Add</a></p>

<c:if test="${not empty vehicle}">
	<p>Vehicle: ${f:h(vehicle.brand)}</p>
</c:if>

<c:if test="${empty items}">
	<p class="notice">No Orders were added.</p>
</c:if>
<c:if test="${not empty items}">
	<table class="grid">
		<tr>
			<th>Id</th>
			<th>Date</th>
			<th>Vehicle</th>
			<th>Action</th>
		</tr>
		<c:forEach var="item" items="${items}" varStatus="rowCounter">
			<tr <c:if test="${rowCounter.count % 2 == 0}">class="alt"</c:if>>
				<td>${f:h(item.id)}</td>
				<td>${f:h(item.date)}</td>
				<td><a href="/vehicle/save?id=${item.vehicleId}">Vehicle (${item.vehicleId})</a></td>
				<td>
					<a href="/order/save?id=${item.id}">Edit</a> &nbsp;
					<a href="/order/delete?id=${item.id}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<%@include file="/carrental/_footer.jsp"%>