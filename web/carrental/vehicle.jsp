<%@page pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="/WEB-INF/tlds/f.tld"%>
<%@include file="/carrental/_header.jsp"%>

<h1>Vehicles</h1>

<p><a href="/vehicle/save">Add</a></p>

<c:if test="${empty items}">
	<p class="notice">No vehicle was added.</p>
</c:if>
<c:if test="${not empty items}">
	<table class="grid">
		<tr>
			<th>Id</th>
			<th>Brand</th>
			<th>Vin</th>
			<th>Action</th>
		</tr>
		<c:forEach var="item" items="${items}" varStatus="rowCounter">
			<tr <c:if test="${rowCounter.count % 2 == 0}">class="alt"</c:if>>
				<td>${f:h(item.id)}</td>
				<td>${f:h(item.brand)}</td>
				<td>${f:h(item.vin)}</td>
				<td>
					<a href="/order?vehicleId=${item.id}">Order</a> &nbsp;
					<a href="/vehicle/save?id=${item.id}">Edit</a> &nbsp;
					<a href="/vehicle/delete?id=${item.id}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>

<%@include file="/carrental/_footer.jsp"%>