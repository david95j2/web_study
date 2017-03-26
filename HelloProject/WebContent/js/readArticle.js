$(document).ready(function() {
	console.log("Hello JavaScript!");

	var $heart = $("#heart");
	var articleNo = $("#article_no").text();
	var userNickName = $("#usernickname").text();
	var userId = $("#memberId").text();
	var likeNo = $("#"+userId+"").text();
	var countzero = $("#countzero").val();
	
	$heart.click(function(){
		if($heart.attr("class") === "fa fa-heart") {
			$heart.attr("class", "fa fa-heart-o")
			$heart.attr("style", "color:#9C9C9C;")
			likedelete(likeNo, articleNo);
		} else if($heart.attr("class") === "fa fa-heart-o") {
			$heart.attr("class", "fa fa-heart")
			$heart.attr("style", "color:red;")
			likeNo = likeupdate(articleNo, userNickName, userId, countzero);
		}
    });
	
});

function likeupdate(articleNo, userNickName, userId, countzero) {
	var likeNo = "";
	
		$.ajax({
	        url:'/board/likeupdate.do',
	        dataType:'json',
	        type : 'post',
	        async: false,
	        data: {article_no : articleNo,
	        	nickname : userNickName,
	        	member_id : userId},
	        success:function(data) {
	        	$("#likeupdown").text(data.totLikeCnt);
	           
	        	if(countzero === undefined) {
	        		if(data.totLikeCnt > 10) {
			        $('#likelist').prepend("<li id='"+data.like_no+"' class='likelist' style='display: none;'><b>"+
			        		"<span id='like_nickname'>"+data.nickname+"</span>"+
			        		"<span id='"+data.member_id+"' style='display: none;'>"+data.like_no+"</span></b></li>");
	        		} else {
	        		$('#likelist').prepend("<li id='"+data.like_no+"' class='likelist'><b>"+
				        	"<span id='like_nickname'>"+data.nickname+"</span>"+
				        	"<span id='"+data.member_id+"' style='display: none;'>"+data.like_no+"</span></b></li>");
	        		}
	        	} else {
	        		$('li').remove("#countzero");
	        		$('#likelist').prepend("<li id='"+data.like_no+"' class='likelist'><b>"+
			        		"<span id='like_nickname'>"+data.nickname+"</span>"+
			        		"<span id='"+data.member_id+"' style='display: none;'>"+data.like_no+"</span></b></li>" +
			        				"<li id='like_end' class='likelist'>님이 좋아합니다.</li>");
	        	}
	            
	            likeNo = data.like_no;
	        }
	    });
		return likeNo;
}

function likedelete(likeNo, articleNo) {
	$.ajax({
        url:'/board/likedelete.do',
        dataType:'json',
        type : 'post',
        async: false,
        data: {like_no : likeNo, 
        		article_no : articleNo},
        success:function(data){
        	$("#likeupdown").text(data.totLikeCnt);
        	if (data.totLikeCnt > 0) {
        		$('li').remove("#"+likeNo+"");
        	} else {
        		$('li').remove('.likelist');
        		$('.likezone').html("<ul id='likelist'><li id='countzero' class='likelist'><p>가장 먼저 좋아요를 눌러주세요.</p></li></ul>");
        	}
            
        }
    });
}