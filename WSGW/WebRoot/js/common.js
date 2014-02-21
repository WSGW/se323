document.write("<script type='text/javascript' src='../js/prototype-1.6.0.3.js'></script>");
function load(url, id) {
	new Ajax.Request(url, {
		method :'get',
		onSuccess : function(req) {
			$(id).innerHTML = req.responseText;
		}
	});
}

function sendRequest(url) {
	new Ajax.Request(url, {
		method :'get',
		asynchronous : false,
		onSuccess : function(req) {
		html = req.responseText;
		}
	});
	return html;
}
