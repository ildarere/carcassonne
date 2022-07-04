for(let x=1; x<65; x++) {
    let div = document.createElement("div");
    div.className = "field";
    div.id = "field_" + x;
    document.body.appendChild(div);
    for(let y=1; y<65; y++) {
        let parent = document.querySelector('#field_' + x)
        let div = document.createElement("div");
        div.className = "tile";


        parent.appendChild(div);
        div.addEventListener('dragover',event=>{onDragOver(event)});
        div.addEventListener('drop',event=>{onDrop(event)});
    }
    }

let dragged;
function onDragStart(event) {
    dragged = event.target;
    event
      .dataTransfer
      .setData('text/plain', event.target.id);

    event
      .currentTarget
      .style
      .backgroundColor = 'yellow';
  }
  function onDragOver(event) {
    event.preventDefault();
  }
  function onDrop(event) {
    const id = event
      .dataTransfer
      .getData('text');
      const draggableElement = document.getElementById(id);
      event.target.appendChild(dragged);
        console.log(event)
      event
      .dataTransfer
      .clearData();
  }



   document.querySelectorAll('.field').forEach(element => {
        element.addEventListener('dragover',event=>{onDragOver(event)});
        element.addEventListener('drop',event=>{onDrop(event)});
    });