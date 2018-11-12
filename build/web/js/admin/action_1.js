 function drop(ev)
  {
  ev.preventDefault();
  var trg = ev.target.getAttribute("newtarget");
  if(trg)
   {
   if(trg!=='true')
    {
    var ch = ev.target.innerHTML;
    ev.target.innerHTML = ev.target.innerHTML + getMyElement();
   }
   }
   else
    {
    if(id!=='row')
     {
     var data = ev.dataTransfer.getData('text');
     ev.target.appendChild(document.getElementById(data));
     }
     else
      {
      var ch = ev.target.innerHTML;
      ev.target.innerHTML = ev.target.innerHTML + getMyElement();
     }
    }
    var obs = document.getElementById(tid).getElementsByTagName('div');
    for(var i = 0;i<obs.length; i ++)
     {
     obs[i].style.position = "relative";
     obs[i].style.top = "";
    }
   }
   function allowDrop(ev)
    {
    ev.preventDefault();
   }
   function drag(ev)
    {
    ev.dataTransfer.setData("text", ev.target.id);;
   }
