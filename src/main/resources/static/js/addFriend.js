'use strict';

document.addEventListener("DOMContentLoaded", ()=>ready());

function ready() {
	addFriendBtn.addEventListener('click', ()=>{
		let val =document.getElementsByTagName('body')[0].id ;
		console.log(val);
		let xhr = new XMLHttpRequest();
		xhr.open('POST', '/id/addFriend', true);

		xhr.addEventListener('readystatechange', ajaxFilterCallback.bind(xhr) );

		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
		xhr.send('q=' + val);

	});
}


function ajaxFilterCallback() {
    let xhr=this;
	if (xhr.readyState !== 4) {
		return;
	}

	if (xhr.status === 200) {
		addFriendBtn.disabled = true;
		addFriendBtn.innerHTML = 'пользователь у вас в друзьях';

	} else {
		alert('Ajax error appear');
	}
}