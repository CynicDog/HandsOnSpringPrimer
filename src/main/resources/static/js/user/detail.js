'use strict';

// processing when loading the screen
jQuery(function($){
    // update button processing
    $('#btn-update').click(function (event) {
        updateUser();
    });

    // delete button processing
    $('#btn-delete').click(function (event) {
        deleteUser();
    });
});

// user update processing
function updateUser() {
    var formData = $('#user-detail-form').serializeArray();

    // ajax communication
    $.ajax({
        type: "PUT",
        cache: false,
        url: '/user/update',
        data: formData,
        dataType: 'json',

    }).done(function(data) {
        // ajax success
        alert('Updated user');
        // redirect to user list screen
        window.location.href = '/user/list';

    }).fail(function(jqXHR, textStatus, errorThrown) {
        // ajax fail
        alert('Failed to update user');

    }).always(function() {
        // Process to always execute
    });
}

// user delete processing
function deleteUser() {
    var formData = $('#user-detail-form').serializeArray();

    // ajax communication
    $.ajax({
        type: "DELETE",
        cache: false,
        url: '/user/delete',
        data: formData,
        dataType: 'json',

    }).done(function(data) {
        // ajax success
        alert('Deleted user');
        // redirect to user list screen
        window.location.href = '/user/list';

    }).fail(function(jqXHR, textStatus, errorThrown) {
        // ajax fail
        alert('Failed to delete user');

    }).always(function() {
        // process to always execute
    })
}