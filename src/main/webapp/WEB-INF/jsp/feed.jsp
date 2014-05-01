<!DOCTYPE html>
<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<jsp:include page="layout/header.jsp" />
<body>
	<jsp:include page="layout/menu.jsp" />
	<!--[if IE]>
	<div class='notie'>
	Sorry but IE has not been fully tested at this time.  Try Firefox or Chrome for best results. 
	</div>
	<![endif]-->
	<div class="container" style='min-width:980px; width:90%'>
    	
    		<h1>Feed List</h1>
    		<div>
    			<div style='text-align: right;white-space: nowrap;'>
		           <input id='feedQuery' value='${query}' />
		           <a href='#' id='search'>Search</a>
		           <a href='#' id='clearFeedSearch'>Clear</a>		       
		       </div>
		       	<div class='clearFix'></div>
		       	<div id='feedList'>
		       		<ul class="pager">
		       			<li><a href="<c:url value="/${pageInfo.previousPage}"/>">&larr; Previous</a></li>
		       			<li><a href="<c:url value="/${pageInfo.nextPage}"/>">Next &rarr;</a></li>
		       		</ul>
		   			<table class="table table-striped">
		   				<c:forEach items="${feeds}" var="feed">
		   					<tr id='tr-${feed.id}'>
		   						<td style='width:90%;'>
		   							<div>
		   								<h3>${feed.title}</h3>
		   								<p>
		   								${feed.description}${!empty(feed.description) ? '<br />' : ''}
		   								URL:&nbsp;
		   								<a class='miniLink plain-link' href="${feed.url}" target='_blank'>${feed.url}</a>
		   								<br/>
		   								Id:&nbsp;${feed.id}
		   								<br/>
		   								Feed Tags:&nbsp;
		   								<c:forEach items="${feed.tags}" var="tag">
		   									${tag}
		   								</c:forEach>
		   								<br/>
		   								<a href='javascript:deleteFeedById("${feed.id}")'>Delete</a>
								        <a href='javascript:editFeed("${feed.id}")'>Edit</a>
								        <a href='javascript:openStatus("${feed.id}")'>Status</a>
								        <a href='javascript:showItems("${feed.id}")'>Items</a>
								        </p>
		   							</div>
		   						</td>
		   					</tr>
		   				</c:forEach>
		   			</table>
		       	</div>
    		</div>
    	<input id='startIdx' type='hidden' value='0' />
	</div>
	<jsp:include page="layout/footer.jsp" />
	<jsp:include page="layout/includeScripts.jsp" />
</body>
</html>