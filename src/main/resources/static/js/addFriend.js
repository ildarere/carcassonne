'use strict';

document.addEventListener("DOMContentLoaded", ()=>ready());

function ready() {
	document.querySelector('#addFriendBtn').addEventListener('click', ()=>{


		let url  = window.location.href;
        let strs =  url.split('/');
        let id = strs.at(-1);
        let sendURL = '/'+ id + '/addFriend';
		let xhr = new XMLHttpRequest();

		xhr.open('POST', sendURL, true);

		xhr.addEventListener('readystatechange', ajaxFilterCallback.bind(xhr) );

		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
		xhr.send('q=' + id.slice(2));

	});
}


function ajaxFilterCallback() {
    let xhr=this;
	if (xhr.readyState !== 4) {
	console.log(2222222222222222222222)
		return;
	}

	if (xhr.status === 200) {
		addFriendBtn.disabled = true;
		addFriendBtn.innerHTML = 'пользователь у вас в друзьях';

	} else {
		alert('Ajax error appear');
	}
}