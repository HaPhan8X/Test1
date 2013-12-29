$(document).ready(function() {
	addCheckAllListener();
});

function addCheckAllListener() {
	$('#checkAll').click(function(){
		var isChecked = $(this).attr("checked") != null ? true : false;
		var countCkbItem=0;
		$('.ckbItem').each(function(){
			countCkbItem++;
			if (isChecked){
				$(this).attr("checked", "checked");
			}
			else{
				$(this).removeAttr("checked");
			}
		});
	});
	$('.ckbItem').click(function(){
		var count = 0;
		var total = $('.ckbItem').length;
		$('.ckbItem').each(function(){
			if ($(this).attr("checked")){
				count++;
			}
		});
		if (count == 0){
			$('#checkAll').removeAttr("checked");
		}else{
			if(count==total){
				$('#checkAll').attr("checked", "checked");
			}else{
				$('#checkAll').removeAttr("checked");
			}
		}
	});
}