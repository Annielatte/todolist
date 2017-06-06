(function(window) {
  'use strict';
  var li = 1;
   var completedList = new Array();
  //  jQuery(document).ready(function($) {
  $("input[name=new-todo]").keydown(function(key) {
    if (key.keyCode == 13) { //enter 누르면 실행
      var value = $('input[name=new-todo]').val();
      if (value.trim() !== "") {
        console.log("li add success : " + value);
        insertTodoList(value);
      } else {
        console.log("li add fail");
      }
      $('input[name=new-todo]').val("");
    }
  });
  //  });

  function deleteTodoList(id) { //할일 삭제
    var json = new Object();

    $.ajax({
      url: "/api/todos/"+id,
      type: "delete",
      success: function(data) {
        init();
      },
      fail: function() {
        alert("실패");
      }
    });
  }

  function todoListView() {
    var str = "";
        var todo_count = 0;
        var notCom_count=0;
    $.ajax({
      url: "/api/todos",
      type: "get",
      success: function(data) {
        console.log('init')

        $.each(data, function(index, val) {
          var li_class = 'new' + index;
          if (val.completed) {
            $('.completed').append('<li class=' + val.id + ' completed>' +
              '<div class="view">' +
              '<input class="toggle" type="checkbox" id='+val.id+'>' +
              '<label>' + val.todo + '</lable>' +
              '<button class="destroy" id='+val.id+'></button>' +
              '</div>' +
              '<input class="edit" value="Rule the web">' +
              '</li>');
               completedList[notCom_count++] = val.id;

          } else {
            todo_count+=1;
            $('.actives').append('<li class=' + val.id + '>' +
              '<div class="view">' +
              '<input class="toggle" type="checkbox" id='+val.id+'>' +
              '<label>' + val.todo + '</lable>' +
              '<button class="destroy" id='+val.id+'></button>' +
              '</div>' +
              '<input class="edit" value="Rule the web">' +
              '</li>');
          }
          //addEvent();
          li = index + 1;
        });
        view_count(todo_count);
      },
      fail: function() {
        alert("실패");
      }
    });
  }
var count =0;

  function view_count(todo_count){ // todo count
    count = todo_count;
    $(".num").html(count);
  }


   addEvent();
  function addEvent(index) {


    $('.destroy').live('click',function() {//x누름
         var id = $(this).attr("id");
            deleteTodoList(id);

    });

    $('.toggle').live('click',function() {//toggle 입력
         var id = $(this).attr("id");
            completedTodoList(id);
    });
  }

  $('a[href="#/"]').click(function(){  //All 필터링
    $(this).addClass("selected");
    $('a[href="#/active"]').removeClass("selected");
    $('a[href="#/completed"]').removeClass("selected");
    $('.actives').show();
    $('.completed').show();
 })


 $('a[href="#/active"]').click(function(){ // active 필터링
    $(this).addClass("selected");
    $('a[href="#/"]').removeClass("selected");
    $('a[href="#/completed"]').removeClass("selected");
    $('.actives').show();
    $('.completed').hide();
 })


 $('a[href="#/completed"]').click(function(){ // completed 필터링
    $(this).addClass("selected");
    $('a[href="#/active"]').removeClass("selected");
    $('a[href="#/"]').removeClass("selected");
    $(this).addClass("selected")
    $('.completed').show();
    $('.actives').hide();
 })

 $('.clear-completed').click(function(){ //clear-completed
   for(var i = 0; i< completedList.length; i++){
          deleteTodoList(completedList[i]);
       }

     });

  function insertTodoList(todoValue) {//할일입력
    var json = new Object(null);
    json.todo = todoValue;
    $.ajax({
      url: "/api/todos",
      type: "post",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(json),
      success: function(data) {
        //alert("추가되었습니다. : " + data.todo);
        init();
      },
      fail: function() {
        alert("실패");
      }
    });
  }

function completedTodoList(id){ //할일 완료
  console.log("completed");
  $.ajax({
    url: "/api/todos/" + id,
    type: "put",
    success: function(data) {
          var toggle = $('#'+id);
          if(toggle.prop('checked') == true){
             $('#'+id).parents('li').addClass('completed');
          }else{
             $('#'+id).parents('li').removeClass('completed');
          }
          view_count(count-1);
          init();
    },
    fail: function() {
      alert("실패");
    }
  });
}


  function clearTodoList() {
    $('.actives').empty();
    $('.completed').empty();
  }

  function init() {
    clearTodoList();
    todoListView();
  }

  init();
})(window);
