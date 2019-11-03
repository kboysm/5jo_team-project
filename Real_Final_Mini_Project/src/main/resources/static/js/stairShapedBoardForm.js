/**
 * 
 */

$(function() {
	showBoard();
	$('#title')[0].oninvalid = function() {
		if (!this.validity.valid && $($(this)[0]).val().length > 0) {
			this.setCustomValidity('50자 이하로 입력하세요.');
		}
	}

	$('#title')[0].oninput = function() {
		this.setCustomValidity("");
	};
	if ($('#writer')[0] != undefined) {
		$('#writer')[0].oninvalid = function() {
			if (!this.validity.valid && $($(this)[0]).val().length > 0) {
				this.setCustomValidity('15자 이하로 입력하세요.');
			}
		}
		$('#writer')[0].oninput = function() {
			this.setCustomValidity("");
		};
	}
	countPost($('#stairContent'));
	$('#stairContent').keyup(function() {
		countPost($(this));
	});
});

function countPost(e) {
	let textLength = e.val().length;
	$('#textCount').text(textLength + '/600');
	if (textLength > 600) {
		e.val(e.val().substr(0, 600));
	}
}

function showBoard() {
	let str = $("#stairContent").text();
	str = str.replace(/<br\s*\/?>/img, '\r\n'); // 엔터 처리
	$("#stairContent").text(str);
}

function updateBoard() {
	let str = $("#stairContent").val();
	str = str.replace(/(?:\r\n|\r|\n)/g, '<br />'); // 엔터 처리
	$("#stairContent").val(str);
}