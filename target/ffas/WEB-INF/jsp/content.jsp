<!DOCTYPE html>
<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach var='parameter' items='${paramValues}'>
	<c:if test="${parameter.key eq 'fq'}">
		<c:forEach var='value' items='${parameter.value}' varStatus="pstat">
			<c:if test="${not empty value}">
				<c:set var="fq" value="${pstat.first ? '' : fq}${value}" />
			</c:if>
		</c:forEach>
	</c:if>
</c:forEach>

<html lang="en">
<jsp:include page="layout/header.jsp" />
<body>
	<jsp:include page="layout/menu.jsp" />
	<!--[if IE]>
	<div class='notie'>
	Sorry but IE has not been fully tested at this time.  Try Firefox or Chrome for best results. 
	</div>
	<![endif]-->
	<div class="container" style='min-width: 980px; width: 90%'>
		<h1>Content List</h1>
		<div class="row">
			
			<div style='text-align: right; white-space: nowrap;'>
				<input id='q' value='${query}' /> <a href='#' id='ssearch'>Search</a> <a
					href='#' id='sclear'>Clear</a> <input type="hidden" id='fq'
					value='${fq}' />
			</div>
			<div class='clearFix'></div>
	       	<div id='contentList'>
				<ul class="pager" style='text-align: left;'>
					<li><a href="<c:url value="/${pageInfo.previousPage}"/>">&larr;
							Previous</a></li>
					<li><a href="<c:url value="/${pageInfo.nextPage}"/>">Next
							&rarr;</a></li>
				</ul>
				<div
					style='width: 100%; border: 2px solid #a1a1a1; border-radius:25px; line-height: 1.75em; margin-top: 1em;'>
					<div
						style='text-align: left; width: 50%; float: left; background-color: inherit;'>
						&nbsp;Showing ${param.start + 1} through ${endCount+0 lt
						totalResults+0 ? endCount : totalResults} of ${totalResults}</div>

					<div
						style='text-align: right; width: 50%; float: right; background-color: inherit;'>
						<a href='${rssUrl}' target='_blank'><img src='<c:url value="/resources/img/feed.png"/>'
							border='0' /></a>&nbsp;
					</div>
				</div>
				<div id="facets" class="col-sm-3">
					<c:if test="${not empty fq}">
						<h4>Current Filters</h4>
						<ul class="facet-list" style='margin-bottom: 1em;'>
							<li><a class='facetClear' href='#'>Remove All Filters</a></li>
							<c:forEach items="${fq}" var="fq">
								<li>${fq}</li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${not empty feedTag}">
						<h4>Feed Tags</h4>
						<ul class="facet-list">
							<c:forEach items="${feedTag}" var="feedTag">
								<li><a class='feedtagFacetLink' href='#'>${feedTag.value}</a>&nbsp;(${feedTag.valueCount})</li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${not empty keyword}">
						<h4>Keywords</h4>
						<ul class="facet-list">
							<c:forEach items="${keyword}" var="keyword">
								<li><a class='keywordFacetLink' href='#'>${keyword.value}</a>&nbsp;(${keyword.valueCount})</li>
						</c:forEach>
					</ul>
					</c:if>
					<c:if test="${not empty site}">
						<h4>Site</h4>
						<ul class="facet-list">
							<c:forEach items="${site}" var="site">
								<li><a class='siteFacetLink' rel='${site.value}' href='#'>
									${fn:length(site.value) gt 20 ? fn:substring(site.value, 0, 20) : site.value}
									${fn:length(site.value) gt 20 ? '...' : ' '}</a>&nbsp;(${site.valueCount})</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
				<div id="results" class="col-sm-9">
					<table id="results-table" class="table table-striped">
							<c:forEach items="${content}" var="entry">
								<tr id='tr-${entry.id}'>
									<td style='width:90%;'>
										<div>
											<h3>${entry.title}</h3>
											${entry.description}${!empty(entry.description) ? '<br />' : '' }
											<br/>
											URL:&nbsp;
											<a href="${entry.link}" target='_blank'>${entry.link}</a>
											<br/>
											Id:&nbsp;${entry.id }
											<br/>
											<c:if test="${not empty entry.keywords}">
												Keywords:&nbsp;
												<c:forEach items="${entry.keywords}" var="keyword" varStatus="kstat">
													<a href='#' title='Find other items with the keyword ${keyword}'>${keyword}</a>${kstat.last == false ? ', ' : '' }
												</c:forEach>
											</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>			
			</div>
		</div>
	</div>
	<jsp:include page="layout/footer.jsp" />
	<jsp:include page="layout/includeScripts.jsp" />
</body>
</html>