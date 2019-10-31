/**
 * 
 */

$(function() {
	showBoard();
	getSmallBoardList(smallNowPage);
	$('#backList').click(backHref);
	besideClickAtr();
});

function besideClickAtr() {
	$('.beside').click(function() {
		console.log($(this).val());
		boardHref($(this).val(), smallNowPage);
	});
}

function arrowClickAtr() {
	$('#arrowLeft').click(function() {
		startPage -= cntPerBlock;
		pageNumUpdate(startPage);
	});
	$('#arrowRight').click(function() {
		startPage += cntPerBlock;
		pageNumUpdate(startPage);
	});
}

function pageNumUpdate(val) {
	getSmallBoardList(val);
}

function backHref() {
	if (category != null) {
		let url = "/stairShapedBoardList?nowPage=" + nowPage + "&category="
				+ category + "&search=" + search;
		location.href = url;
	} else
		location.href = "/stairShapedBoardList?nowPage=" + nowPage;
}

function boardHref(no, smallNowPage) {
	if (category != null) {
		let url = "/stairShapedBoard?no=" + no + "&nowPage=" + nowPage
				+ "&category=" + category + "&search=" + search
				+ "&smallNowPage=" + smallNowPage;
		location.href = url;
	} else
		location.href = "/stairShapedBoard?no=" + no + "&nowPage=" + nowPage
				+ "&smallNowPage=" + smallNowPage;
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

function getSmallBoardList(smallNowPage) {
	$.ajax({
		url : 'stairShapedBoardSmallList',
		type : 'get',
		data : {
			'nowPage' : nowPage,
			'smallNowPage' : smallNowPage,
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