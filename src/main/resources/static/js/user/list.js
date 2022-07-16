'use strict';

var userData = null;
var table = null;

// Processing when loading the screen
jQuery(function($) {
    // data table initialization
    createDataTables();

    // search button processing
    $('#btn-search').click(function (event) {
        search();
    });

    // Event when the download button(JS) is clicked
    $('#download-java-script').click(function() {
        event.preventDefault();

        var xmlHttpRequest = new XMLHttpRequest();

        // request destination
        xmlHttpRequest.open('POST', '/user/list/download', true);

        // response type
        xmlHttpRequest.responseType = 'blob';

        // define successful request processing
        xmlHttpRequest.onload = function(e) {
            var fileName = 'userListJavaScript.csv';

            fileSave(this.response, fileName);
        };

        // CSRF measures
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";

        xmlHttpRequest.setRequestHeader(header, token);

        xmlHttpRequest.send();
    });

    // Event when the download button(jQuery) is clicked
    $('#download-jquery').click(function() {
        event.preventDefault();

        // Get value form
        var formData = $('#download-form').serializeArray();

        $.ajax({
            type:'post',
            url:'/user/list/download',
            data:formData,
            xhrFields:{
                responseType:'blob'
            },
        })

            .done(function(data, status, jqXHR) {
                var fileName='userListJQuery.csv';
                const blob = new Blob([data], {type:data.type});

                fileSave(data, fileName);
            })

            .fail(function(jqXHR, status, errorThrown){
                alert("File download failure");
            })

            .always(function(data, status, errorThrown) {
                // process to always execute
            })
    });

});

function fileSave(blob, fileName) {
    // in case of using IE browser
    if (window.navigator.msSaveBlob) {
        window.navigator.msSaveBlob(blob, fileName);
    }

    // in case of using others
    else {
        var a = document.createElement('a');
        var blobUrl = window.URL.createObjectURL(blob);

        document.body.appendChild(a);
        a.style = 'display:none';
        a.href = blobUrl;
        a.download = fileName;
        a.click();
    }
}

function search() {
    // get the value form
    var formData = $('#user-search-form').serialize();

    // ajax communication
    $.ajax({
        type:"GET",
        url:'/user/get/list',
        cache:false,
        data:formData,
        dataType:'json',
        contentType:'application/json; charset=UTF-8',
        timeout:5000,

    }).done(function(data) {
        console.log(data);
        userData = data;
        createDataTables();

    }).fail(function(jqXHR, textStatus, errorThrown) {
        alert("search process failed");

    }).always(function() {
        // process to always execute
    });
}

function createDataTables() {
    if (table !== null) {
        table.destroy();
    }

    table = $('#user-list-table').DataTable({
        // Display data
        data: userData,
        columns: [
            {data:'userId'},
            {data:'userName'},
            {data:'birthday',
                render: function(data, type, row) {
                    var date = new Date(data);
                    var year = date.getFullYear();
                    var month = date.getMonth() + 1;
                    var date = date.getDate();

                    return date + '/' + month + '/' + year;
                }
            },
            {data:'age'},
            {data:'gender',
                render: function(data, type, row) {
                    var gender = '';
                    if (data === 1) {
                        gender = 'Male';
                    } else {
                        gender = 'Female';
                    }

                    return gender;
                }
            },
            {data:'userId',
                render: function(data, type, row) {
                    var url = '<a href="/user/detail/' + data + '">Detail</a>';

                    return url;
                }
            },
        ]
    })
}