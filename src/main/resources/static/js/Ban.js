'use strict';

document.addEventListener("DOMContentLoaded", ()=>ready());

function ready() {
	BanUser.addEventListener('click', ()=>{
		 console.log();
        let url  = window.location.href;
        let strs =  url.split('/');
        let id = strs.at(-1);
        let sendURL = '/'+ id + '/ban';

		let xhr = new XMLHttpRequest();
		xhr.open('POST', sendURL, true);

		xhr.addEventListener('readystatechange', checkCallback.bind(xhr) );

		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')

		xhr.send('id=' + id.slice(2));

	});
}


function checkCallback() {
    let xhr=this;
	if (xhr.readyState !== 4) {
		return;
	}

	if (xhr.status === 200) {
		alert('user has been banned')

	} else {
		alert('Ajax error appear');
	}
}