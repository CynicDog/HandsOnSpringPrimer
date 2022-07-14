'user strict';

// processing when loading the screen
jQuery(function($){

    // signup button processing
    $('#btn-signup').click(function (event) {
        signupUser();
    });
});

// user signup process
function signupUser() {
    // clear validation results
    removeValidResult();

    // get the value of the form
    var formData = $('#signup-form').serializeArray();

    $.ajax({
        type: "POST",
        cache: false,
        url: '/user/signup/rest',
        data: formData,
        dataType: 'json',

    }).done(function(data) {
        console.log(data);

        if(data.result === 90) {
            $.each(data.errors, function(key, value) {
            reflectValidResult(key, value)
            });
        }  else if (data.result === 0) {
            alert("Signed up user");
            window.location.href='/login';
        }

    }).fail(function(jqXHR, textStatus, errorThrown) {
        alert("User signup failed");

    }).always(function() {
        // process to always execute
    });
}

// clear validation results
function removeValidResult() {
    $('.is-invalid').removeClass('is-invalid');
    $('.invalid-feedback').remove();
    $('.text-danger').remove();
}

// reflection of the validation result
function reflectValidResult(key, value) {
    // add error message
    if (key === 'gender') {
        // apply CSS
        $('input[name=' + key + ']').addClass('is-invalid');
        // add error message
        $('input[name=' + key + ']').parent().parent()
            .append('<div class="text-danger">' + value + '</div>')
    } else {
        // apply CSS
        $('input[id=' + key + ']').addClass('is-invalid');
        //add error message
        $('input[id=' + key + ']').after('<div class="invalid-feedback">' + value + '</div>');
    }
}