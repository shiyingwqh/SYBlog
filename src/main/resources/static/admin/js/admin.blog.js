function submit() {
    var data = {};
    data.title = document.getElementById("title").value;
    data.text = editor.getMarkdown();
    if (data.title.length < 1 || data.text.length < 1) {
        alert("Title and Text Can't be Empty!");
        return;
    }
    console.log(data);
    $.ajax({
        url: "/api/upload-blog",
        xhrFields: {
            withCredentials: true
        },
        contentType: 'application/json;charset=UTF-8',
        dataType: 'JSON',
        data: JSON.stringify(data),
        type: "POST",
        success: function (r) {
            if (r.status === 200) {
                alert("Success!")
                location.reload()
            } else {
                console.log(r)
            }
        }
    })
}
function modify(id){
    var data = {}
    data.id = id
    data.title = document.getElementById("title").value
    data.text = editor.getMarkdown();
    if (data.title === "" || data.text === ""){}
    console.log(data)
    $.ajax({
        url:"/api/modify-blog",
        xhrFields: {
            withCredentials: true
        },
        contentType:'application/json;charset=UTF-8',
        dataType:'JSON',
        data:JSON.stringify(data),
        type:"POST",
        success:function (r){
            if (r.status === 200){
                alert("Success!")
            }else {
                alert(r)
            }
        }
    })
}
function del(id){
    $.ajax({
        url:"/api/delete-blog?id=" + id,
        xhrFields: {
            withCredentials: true
        },
        data:null,
        type:"POST",
        success:function (r){
            if (r.status === 200){
                window.location.href = "/admin"
            }else {
                console.log(r)
            }
        }
    })
}