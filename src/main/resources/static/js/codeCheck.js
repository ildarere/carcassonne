'use strict';

document.addEventListener("DOMContentLoaded", ()=>ready());

function ready() {
	check.addEventListener('click', ()=>{
		let VerCode  = code.value
		let xhr = new XMLHttpRequest();
		xhr.open('POST', '/send-mail/verifyCode', true);

		xhr.addEventListener('readystatechange', checkCallback.bind(xhr) );

		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
		console.log(VerCode)
		xhr.send('code=' + VerCode);

	});
}


function checkCallback() {
    let xhr=this;
	if (xhr.readyState !== 4) {
		return;
	}

	if (xhr.status === 200) {
		let data = xhr.responseText;
		if (data.length > 0) {
        			try {
        				let dataJson = JSON.parse(data);
        				console.log(dataJson)
                        if(dataJson == true){
                        window.location.replace("/");
                        		}
                        else{
                        alert("wrong code")
                        		}
        			} catch(e) {

        			}
        		}



	} else {
		alert('Ajax error appear');
	}
}