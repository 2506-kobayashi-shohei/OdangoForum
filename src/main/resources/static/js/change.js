$(function(){
	$('.change').on('click', function() {
		if(!confirm("ユーザー停止状態の変更を行います。よろしいですか？")){
		    location.reload();
	        return false;
	    }else{
	    }
	});
});