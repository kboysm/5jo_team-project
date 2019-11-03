/**
 * 
 */
let nowPage;
let category;
let search;

$(function() {
	nowPage=$('#nowPage').val();
	category=$('#category').val();
	search=$('#search').val();
	showBoard();
	getSmallBoardList(smallNowPage);
	$('#backList').click(backHref);
	besideClickAtr();
});

function besideClickAtr() {
	$('.beside').click(function() {
		boardHref($(this).val(), 1);
	});
	
}

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
	getSmallBoardList(val);
}

function backHref() {
	if (category != null && category.length!=0) {
		let url = "/stairShapedBoardList?nowPage=" + nowPage + "&category="
				+ category + "&search=" + search;
		location.href = url;
	} else
		location.href = "/stairShapedBoardList?nowPage=" + nowPage;
}

function boardHref(no, smallNowPage2) {
	if (category != null && category.length!=0) {
		let url = "/stairShapedBoard?no=" + no + "&nowPage=" + nowPage
				+ "&category=" + category + "&search=" + search
				+ "&smallNowPage=" + smallNowPage2;
		location.href = url;
	} else
		location.href = "/stairShapedBoard?no=" + no + "&nowPage=" + nowPage
				+ "&smallNowPage=" + smallNowPage2;
}

function showBoard() {
	let str = $("#stairContent").text();
	str = str.replace(/<br\s*\/?>/img, '\r\n'); // 엔터 처리
	$("#stairContent").text(str);
}

function deleteBoard(no) {
	if (confirm("삭제하시겠습니까?"))
		location.href = "/stairShapedBoardDelete?no=" + no;
}

function getSmallBoardList(smallNowPage2) {
	$.ajax({
		url : 'stairShapedBoardSmallList',
		type : 'get',
		data : {
			'nowPage' : nowPage,
			'smallNowPage' : smallNowPage2,
			'grpno' : grpno,
			'no' : no
		},
		success : function(data) {
			$('#smallListDiv').html(data);
			$('.pageNum').click(function() {
				getSmallBoardList($(this).text())
			});
			arrowClickAtr();
		},
		error : function(error) {
			alert("error " + error.name);
		}
	});
}