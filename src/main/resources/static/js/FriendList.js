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
		let dataJson = JSON.parse(data);
		    let blocks = document.querySelectorAll('.gameSubMain2');
		for(let b of blocks){
		    b.remove();
		}
        if (data.length > 0) {
                try {
                    let gameMain = document.querySelector('#gameMain');
                    console.log(dataJson)
                    dataJson.forEach(p=>{
                    let div = document.createElement('div')
                    div.classList.add("gameSubMain2")
                    let img = document.createElement('img')
                    img.classList.add("imgPlus")
                    let pict ="img/gameLogo.png"
                    img.scr = pict;
                    let a = document.createElement('a');
                    a.classList.add("playerName");
                    a.innerHTML=p.name;
                    let link = "/id"+p.id;
                    a.href = link;
                    let div2 = document.createElement('div');
                    div2.classList.add("default");
                    div2.innerHTML="Рейтинг:"+p.rating;

                    div.appendChild(img);
                    div.appendChild(a);
                    div.appendChild(div2);




                    gameMain.appendChild(div);
                    })
                } catch(e) {

                }
            }

	} else {
		alert('Ajax error appear');
	}
}