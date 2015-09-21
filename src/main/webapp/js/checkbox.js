$(document).ready( function() {
	var $checkboxes = $('.checkbox :checkbox[name="checkboxes"]');
	var $checkedCheckboxes;
	
	$checkboxes.click(function() {
		
		$checkedCheckboxes = $('.checkbox :checkbox[name="checkboxes"]:checked');
		if($checkedCheckboxes.length) {
	        $checkboxes.removeAttr('required');
	    } else {
	    	alert($checkedCheckboxes.length);
	        $checkboxes.attr('required', 'required');
	    }
	 });		
});
