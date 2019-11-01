/**
 * 
 */
let category;
let search;

$(function() {
	category=$('#category').val();
	search=$('#search').val();
	$(".pageNum").click(pageHref);
	arrowClickAtr();
})

function arrowClickAtr() {
	let startPage=parseInt($('#startPage').val());
	let cntPerBlock=parseInt($('#cntPerBlock').val());
	$('#arrowLeft').click(function() {
		pageNumUpdate(startPage-cntPerBlock);
	});
	$('#arrowRight').click(function() {
		pageNumUpdate(startPage+cntPerBlock);
	});
}

function pageNumUpdate(val) {
	location.href = "/stairShapedBoardList?nowPage=" + val;
}

function pageHref() {
	if (category != null && category.length!=0) {
		let url = "/stairShapedBoardList?nowPage=" + $(this).text()
				+ "&category=" + category + "&search=" + search;
		location.href = url;
	} else
		location.href = "/stairShapedBoardList?nowPage=" + $(this).text();
}

function boardHref(no, page) {
	if (category != null && category.length!=0) {
		let url = "/stairShapedBoard?no=" + no + "&nowPage=" + page
				+ "&category=" + category + "&search=" + search;
		location.href = url;
	} else
		location.href = "/stairShapedBoard?no=" + no + "&nowPage=" + page;
}