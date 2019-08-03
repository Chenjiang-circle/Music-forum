$(document).ready(function(){
     $("#fly-nav").mouseover(function(){
        var a=document.getElementById('fly-nav');
        var scroll_width = 100;
        a.onmousewheel = mousewheel_event;
        function mousewheel_event(e){
            var e=e||window.event,v;
            e.wheelDelta ? v=e.wheelDelta : v=e.detail;
            if(v>3||-v>3) v=-v;
            v>0 ? a.scrollLeft+=scroll_width : a.scrollLeft-=scroll_width;
    
            e.preventDefault();
        }
    })

    $("#fly-albums").mouseover(function(){
        var a=document.getElementById('fly-albums');
        var scroll_width = 100;
        a.onmousewheel = mousewheel_event;
        function mousewheel_event(e){
            var e=e||window.event,v;
            e.wheelDelta ? v=e.wheelDelta : v=e.detail;
            if(v>3||-v>3) v=-v;
            v>0 ? a.scrollLeft+=scroll_width : a.scrollLeft-=scroll_width;
    
            e.preventDefault();
        }

    })
})