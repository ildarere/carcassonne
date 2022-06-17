'use strict';

document.addEventListener("DOMContentLoaded", ()=>ready());

function ready() {
	findButton.addEventListener('click', ()=>{
		let name = findField.value;

		let xhr = new XMLHttpRequest();
		xhr.open('POST', "/friends/find", true);

		xhr.addEventListener('readystatechange', FriendsCallback.bind(xhr) );

		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')

		xhr.send('name=' + name);

	});
}


function FriendsCallback() {
    let xhr=this;
	if (xhr.readyState !== 4) {
		return;
	}

	if (xhr.status === 200) {
		let data = xhr.responseText;
		//TODO:очистить список друзей
        if (data.length > 0) {
                try {
                    let dataJson = JSON.parse(data);
                    console.log(dataJson)
                    //TODO:добавить вывод друзей
                } catch(e) {

                }
            }

	} else {
		alert('Ajax error appear');
	}
}