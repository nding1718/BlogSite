"use strict";
//# sourceURL=main.js

// DOM execute after loading page
$(function() {

	// menu event
	$(".blog-menu .list-group-item").click(function() {
 
		var url = $(this).attr("url");
		
		// remove other click format first and then add the current one
		$(".blog-menu .list-group-item").removeClass("active");
		$(this).addClass("active");  
 
		// load the pages of other module to right work place
		 $.ajax({ 
			 url: url, 
			 success: function(data){
				 $("#rightContainer").html(data);
		 },
		 error : function() {
		     alert("error");
		     }
		 });
	});
	
	
	// pickup the first item in the menu
	 $(".blog-menu .list-group-item:first").trigger("click");
});