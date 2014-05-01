<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Place at the end of the document so the pages load faster -->
<script type='text/javascript' src='<c:url value="/resources/js/jquery-1.10.2.min.js"/>'></script>
<script type='text/javascript' src='<c:url value="/resources/js/bootstrap.min.js"/>'></script>
<script type='text/javascript' src='<c:url value="/resources/js/manager.js"/>'></script>
<script type='text/javascript' src='<c:url value="/resources/js/utils.js"/>'></script>

<script type='text/javascript'>
	
	

	$(document).ready(function() {
		$("#search").button();
		$("#search").click(function() {
			feedSearch();
		});
		
		$("#feedQuery").keyup(function(event) {
			if (event.keyCode == 13) {
				$("#search").click();
			}
		});
		
		$("#clear").button();
		$("#clear").click(function() {
			$("feedQuery").val('');
			feedSearch();
		});
		
		
		$("#ssearch").button();    
	    $("#ssearch").click(function() {
	    	$('#fq').val('');
	        contentSearch();
	    });
	    
	    $("#q").keyup(function(event) {
			if (event.keyCode == 13) {
				$("#ssearch").click();		
			}
		});
	    
	    $("#sclear").button();    
	    $("#sclear").click(function() {
	    	$('#fq').val('');
	    	$('#q').val('');
	        contentSearch();
	    });
	    
	    $(".feedtagFacetLink").click(function() {
    	   var qt = $(this).text();
    	   var fqVal = $('#fq').val();
    	   if (fqVal != '') {
    		   fqVal += ',feedtag:' + qt;
    	   } else {
    		   fqVal += 'feedtag:' + qt;
    	   }
    	   
    	   $('#fq').val(fqVal);
    	   
    	   contentSearch();
    	   return false; 
    	});

    	$(".keywordFacetLink").click(function() {
    	   var qt = $(this).text();
    	   var fqVal = $('#fq').val();
    	   
    	   if (fqVal != '') {
    		   fqVal += ',keyword:' + qt;
    	   } else {
    		   fqVal += 'keyword:' + qt;
    	   }   
    	   $('#fq').val(fqVal);
    	      
    	   contentSearch();
    	   return false; 
    	});

    	$(".siteFacetLink").click(function() {
    	    var qt = $(this).attr('rel');
    	    var fqVal = $('#fq').val();
    	    if (fqVal != '') {
    		    fqVal += ',site:' + qt;
    	    } else {
    		    fqVal += 'site:' + qt;
    	    }   
    	    $('#fq').val(fqVal);
    		
    	    contentSearch();
    		return false; 
    	});

    	$(".facetClear").click(function() {
    	   $('#fq').val('');
    	   contentSearch();
    	   return false; 
    	});
	});
</script>