/**
 * 
 */
$(function() {
	$(".pageNum").click(pageHref);
	arrowClickAtr();
})

function arrowClickAtr() {
	$('#arrowLeft').click(function() {
		pageNumUpdate(startPage - cntPerBlock);
	});
	$('#arrowRight').click(function() {
		pageNumUpdate(startPage + cntPerBlock);
	});
}

function pageNumUpdate(val) {
	location.href = "/stairShapedBoardList?nowPage=" + val;
}

function pageHref() {
	if (category != null) {
		let url = "/stairShapedBoardList?nowPage=" + $(this).text()
				+ "&category=" + category + "&search=" + search;
		location.href = url;
	} else
		location.href = "/stairShapedBoardList?nowPage=" + $(this).text();
}

function boardHref(no, page) {
	if (category != null) {
		let url = "/stairShapedBoard?no=" + no + "&nowPage=" + page
				+ "&category=" + category + "&search=" + search;
		location.href = url;
	} else
		location.href = "/stairShapedBoard?no=" + no + "&nowPage=" + page;
}