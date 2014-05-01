<!DOCTYPE html>
<jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" />
<html lang="en">
<jsp:include page="layout/header.jsp" />
<body>
	<jsp:include page="layout/menu.jsp" />
	<!--[if IE]>
	<div class='notie'>
	Sorry but IE has not been fully tested at this time.  Try Firefox or Chrome for best results. 
	</div>
	<![endif]-->
	<div class="container" style='width:90%'>
	<div class="jumbotron">
	  <h1>Hello, world!</h1>
	  <p>This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
	  <p><a class="btn btn-primary btn-lg" role="button">Learn more</a></p>
	</div>
</div>
	<jsp:include page="layout/footer.jsp" />
	<jsp:include page="layout/includeScripts.jsp" />
</body>
</html>