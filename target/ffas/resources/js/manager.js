function contentSearch() {
	var query = $('#q').val();
    var fqs = $('#fq').val();
    
    var searchUrl = 'content?query=' + encodeURIComponent(query) + '&fq=' + escape(fqs);
    window.location.href = searchUrl;
}

function feedSearch() {
	var query = $('#feedQuery').val();
	
	var searchUrl = 'feed?query=' + encodeURIComponent(query);
   	window.location.href = searchUrl;
}